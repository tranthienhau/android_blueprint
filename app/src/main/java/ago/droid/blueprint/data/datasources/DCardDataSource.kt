package ago.droid.blueprint.data.datasources

import ago.droid.blueprint.data.models.DCardModel
import kotlinx.coroutines.delay
import javax.inject.Inject

interface DCardDataSource {
    suspend fun getCards(): List<DCardModel>;
}

class DCardDataSourceImpl @Inject constructor() : DCardDataSource {
    override suspend fun getCards(): List<DCardModel> {
        delay(2000L);
        return buildData();
    }

    private fun buildData(): List<DCardModel> {
        var cards = ArrayList<DCardModel>()
        var images = ArrayList<String>()
        images.add("https://res.cloudinary.com/dtltilgkm/image/upload/v1594222810/dragon_4_4084909a6c.jpg")
        cards.add(DCardModel("Fames volutpat.","Dui mattis risus elit purus feugiat quis in sit.", images))

        images = ArrayList<String>()
        images.add("https://res.cloudinary.com/dtltilgkm/image/upload/v1595010562/dragon_3_0ab5eb6580.png")
        images.add("https://res.cloudinary.com/dtltilgkm/image/upload/v1596118709/images_7b46fd6b39.png")
        images.add("https://res.cloudinary.com/dtltilgkm/image/upload/v1596118961/How_many_eggs_do_bearded_dragons_lay_e976f7991e.png")
        cards.add(DCardModel("Suspendisse vel.","Dui mattis risus elit purus feugiat quis in sit.", images))
        return cards
    }

}