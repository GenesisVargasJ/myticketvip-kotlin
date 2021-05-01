package co.dstic.myticketvip.data.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class User(
    @SerializedName("id")
    val id: String?,
    @SerializedName("uid")
    val uid: String?,
    @SerializedName("cid")
    val cid: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("address")
    val address: String?
)