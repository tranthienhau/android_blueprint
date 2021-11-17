package ago.droid.blueprint.data.repositories

import ago.droid.blueprint.data.datasources.ComponentApiSource
import ago.droid.blueprint.data.datasources.ComponentDataSource
import ago.droid.blueprint.data.datasources.DCardDataSource
import ago.droid.blueprint.domain.entities.Component
import ago.droid.blueprint.domain.entities.DCard
import ago.droid.blueprint.domain.repositories.ComponentRepository
import ago.droid.blueprint.domain.repositories.DCardRepository
import javax.inject.Inject

class ComponentRepositoryImpl @Inject constructor(private val componentDataSource: ComponentDataSource) : ComponentRepository {
    override suspend fun getComponents(): List<Component> {
        val result = componentDataSource.getComponents()
        return result.map { it -> Component(it.text, it.url) };
    }
}