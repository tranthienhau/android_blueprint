package ago.droid.blueprint.data.datasources

import ago.droid.blueprint.data.models.ComponentModel
import ago.droid.blueprint.data.remote.WebApi
import android.util.Log
import kotlinx.coroutines.*
import javax.inject.Inject


interface ComponentApiSource {
    suspend fun getComponents(): List<ComponentModel>;
}

class ComponentApiSourceImpl @Inject constructor(private val webApi: WebApi) : ComponentApiSource {
    override suspend fun getComponents(): List<ComponentModel> {

        val deferred = CoroutineScope(Dispatchers.IO).async {
             webApi.getPeopleAsync()
        }
        var data = deferred.await()
        Log.d("getPeopleAsync:", data.size.toString())
        return ArrayList()

        /*webApi.getPeople().enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {

            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }

        })*/

    }
}