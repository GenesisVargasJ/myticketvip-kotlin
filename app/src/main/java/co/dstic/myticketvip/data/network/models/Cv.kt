package co.dstic.myticketvip.data.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Cv(
    @SerializedName("id")
    var id: String?,
    @SerializedName("id_vehicle")
    val id_vehicle: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("km")
    val km: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("date_expedition")
    val date_expedition: String?,
    @SerializedName("date_expire")
    val date_expire: String?,
    @SerializedName("id_partner")
    val id_partner: String?,
    @SerializedName("url_image")
    val url_image: String?,
    @SerializedName("value")
    val value: String? = null,
)