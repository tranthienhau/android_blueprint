package ago.droid.blueprint.domain.usecases

import ago.droid.blueprint.domain.entities.DCard
import ago.droid.blueprint.domain.repositories.DCardRepository
import java.lang.Exception
import javax.inject.Inject

class FetchDCardsUseCase constructor(private val dCardRepository: DCardRepository) : BaseUseCase<List<DCard>, Unit>{

    override suspend fun invoke(param: Unit): List<DCard> {
        var result = emptyList<DCard>();
        try {
            result = dCardRepository.getCards();

        }catch (e: Exception){
        }
        return result;
    }
}