package ago.droid.blueprint.data.repositories

import ago.droid.blueprint.data.datasources.DCardDataSource
import ago.droid.blueprint.domain.entities.DCard
import ago.droid.blueprint.domain.repositories.DCardRepository
import javax.inject.Inject

class DCardRepositoryImpl @Inject constructor(private val dCardDataSource: DCardDataSource) : DCardRepository {
    override suspend fun getCards(): List<DCard> {
        val cards = dCardDataSource.getCards()
        return cards.map { it -> DCard(it.header,it.description, it.images) }
    }

}