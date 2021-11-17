package ago.droid.blueprint.data.models

import ago.droid.blueprint.domain.entities.DCard

class DCardModel(header: String, description: String, images: MutableList<String>) : DCard(header, description, images)