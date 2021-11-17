package ago.droid.blueprint.domain.repositories

import ago.droid.blueprint.domain.entities.DCard

interface DCardRepository {
    suspend fun getCards() : List<DCard>
}