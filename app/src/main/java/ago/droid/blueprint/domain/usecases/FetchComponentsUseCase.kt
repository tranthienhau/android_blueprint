package ago.droid.blueprint.domain.usecases

import ago.droid.blueprint.data.models.ComponentModel
import ago.droid.blueprint.domain.entities.Component
import ago.droid.blueprint.domain.repositories.ComponentRepository
import java.lang.Exception
import javax.inject.Inject

class FetchComponentsUseCase constructor(private val componentRepository: ComponentRepository) : BaseUseCase<List<Component>, Unit>{
    override suspend fun invoke(param: Unit): List<Component> {
        var result = emptyList<Component>();
        try {
            result = componentRepository.getComponents();
        }catch (e: Exception){

        }
        return result;
    }

}