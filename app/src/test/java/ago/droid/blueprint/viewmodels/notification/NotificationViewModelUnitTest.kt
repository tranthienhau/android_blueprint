package ago.droid.blueprint.viewmodels.notification

import ago.droid.blueprint.data.models.ComponentModel
import ago.droid.blueprint.domain.entities.Component
import ago.droid.blueprint.domain.repositories.ComponentRepository
import ago.droid.blueprint.domain.usecases.FetchComponentsUseCase
import ago.droid.blueprint.until.TestCoroutineRule
import ago.droid.blueprint.viewmodels.notifications.NotificationsViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NotificationViewModelUnitTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()//For LiveData

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var componentRepository: ComponentRepository


    @Mock
    private lateinit var componentsObserver: Observer<List<Component>>


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    fun getComponents_isSuccess() = runBlocking {
        var components = ArrayList<Component>()
        components.add(ComponentModel("Test Component","https://www.google.com/"))


        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(components)
                .`when`(componentRepository)
                .getComponents()

            var fetchComponentsUseCase = FetchComponentsUseCase(componentRepository)
            var notificationsViewModel = NotificationsViewModel(fetchComponentsUseCase)

            notificationsViewModel.components.observeForever(componentsObserver)
            Mockito.verify(componentRepository).getComponents()
            Mockito.verify(componentsObserver).onChanged(components)

            notificationsViewModel.components.removeObserver(componentsObserver)
        }
    }

    @Test
    fun getComponents_error() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            Mockito.doThrow(RuntimeException(errorMessage))
                .`when`(componentRepository)
                .getComponents()

            var fetchComponentsUseCase = FetchComponentsUseCase(componentRepository)
            var notificationsViewModel = NotificationsViewModel(fetchComponentsUseCase)

            notificationsViewModel.components.observeForever(componentsObserver)
            Mockito.verify(componentRepository).getComponents()
            Mockito.verify(componentsObserver).onChanged(emptyList())

            notificationsViewModel.components.removeObserver(componentsObserver)
        }
    }

    @After
    fun tearDown() {}
}