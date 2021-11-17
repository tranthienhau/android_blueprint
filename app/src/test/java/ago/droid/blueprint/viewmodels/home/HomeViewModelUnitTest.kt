package ago.droid.blueprint.viewmodels.home

import ago.droid.blueprint.data.models.DCardModel
import ago.droid.blueprint.domain.entities.DCard
import ago.droid.blueprint.domain.repositories.DCardRepository
import ago.droid.blueprint.domain.usecases.FetchDCardsUseCase
import ago.droid.blueprint.navigation.Navigator
import ago.droid.blueprint.until.TestCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class HomeViewModelUnitTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()//For LiveData

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var dCardRepository: DCardRepository

    lateinit var fetchDCardsUseCase: FetchDCardsUseCase;

    @Mock
    lateinit var navigator : Navigator

    private lateinit var homeViewModel: HomeViewModel;

    @Mock
    private lateinit var cardsObserver: Observer<List<DCard>>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    fun getCards_isSuccess() = runBlocking {
        var cards = ArrayList<DCardModel>()
        var images = ArrayList<String>()
        images.add("https://res.cloudinary.com/dtltilgkm/image/upload/v1594222810/dragon_4_4084909a6c.jpg")
        cards.add(DCardModel("Test dCard","Dui mattis risus elit purus feugiat quis in sit.", images))
//        var liveCards : MutableLiveData<List<DCard>> = MutableLiveData();
//        liveCards.value = cards;
        testCoroutineRule.runBlockingTest {
            doReturn(cards)
                .`when`(dCardRepository)
                .getCards()

            fetchDCardsUseCase = FetchDCardsUseCase(dCardRepository)
            homeViewModel = HomeViewModel(fetchDCardsUseCase, navigator)

            homeViewModel.cards.observeForever(cardsObserver)
            verify(dCardRepository).getCards()
            verify(cardsObserver).onChanged(cards)

            homeViewModel.cards.removeObserver(cardsObserver)
        }
    }

    @Test
    fun getCards_error() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(dCardRepository)
                .getCards()

            fetchDCardsUseCase = FetchDCardsUseCase(dCardRepository)
            homeViewModel = HomeViewModel(fetchDCardsUseCase, navigator)

            homeViewModel.cards.observeForever(cardsObserver)
            verify(dCardRepository).getCards()
            verify(cardsObserver).onChanged(emptyList())
            homeViewModel.cards.removeObserver(cardsObserver)
        }
    }

    @After
    fun tearDown() {}
}