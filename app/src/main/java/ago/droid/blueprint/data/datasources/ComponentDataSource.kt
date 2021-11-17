package ago.droid.blueprint.data.datasources

import ago.droid.blueprint.data.models.ComponentModel
import ago.droid.blueprint.domain.entities.Component
import kotlinx.coroutines.delay
import javax.inject.Inject

interface ComponentDataSource {
    suspend fun getComponents(): List<ComponentModel>;
}

class ComponentDataSourceImpl @Inject constructor() : ComponentDataSource {
    override suspend fun getComponents(): List<ComponentModel> {
        delay(2000L);
        return buildData();
    }

    private fun buildData(): List<ComponentModel> {
        var components = ArrayList<ComponentModel>()
        components.add(ComponentModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ","https://www.google.com/"))
        components.add(ComponentModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ","https://www.google.com/"))
        components.add(ComponentModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ","https://www.google.com/"))
        components.add(ComponentModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Facilisi nunc non, luctus fringilla tempus. Curabitur est. ","https://www.google.com/"))
        components.add(ComponentModel("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id vestibulum nisl auctor. ","https://www.google.com/"))
        return components
    }
}