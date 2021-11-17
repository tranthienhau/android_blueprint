package ago.droid.blueprint.data.remote

import ago.droid.blueprint.domain.entities.User
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface WebApi {
    @GET("users")
    suspend fun getPeopleAsync(): List<User>

    @FormUrlEncoded
    @POST("update-user-locatin")
    fun configure(@Field("province_id") province_id: String): Call<String>

    @Multipart
    @POST("profile")
    fun updateProfileV2(@Part filePart: MultipartBody.Part?,
                        @Part("first_name") firstName: RequestBody): Call<String>
}