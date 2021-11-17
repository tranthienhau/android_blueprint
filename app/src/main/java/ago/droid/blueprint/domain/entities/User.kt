package ago.droid.blueprint.domain.entities

import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("avatar")
    val image: String,
    @SerializedName("email")
    val userEmail: String,
    @SerializedName("id")
    val userId: String,
    @SerializedName("name")
    val userName: String
)