package co.dstic.myticketvip.data.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Partner(
    @SerializedName("id")
    val id: String,
    @SerializedName("uid")
    val uid: String?,
    @SerializedName("cid")
    val cid: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_person")
    val name_person: String?,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("coordinates")
    val coordinates: String,
    @SerializedName("id_partner_type")
    val id_partner_type: List<String>,
    @SerializedName("partner_type")
    val partner_type: String?,
    @SerializedName("schedule")
    val schedule: String,
    @SerializedName("schedule_start")
    val schedule_start: String,
    @SerializedName("schedule_end")
    val schedule_end: String,
    @SerializedName("id_city")
    val id_city: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("brands")
    val brands: String,
    @SerializedName("id_vehicle_type")
    val id_vehicle_type: List<String>,
    @SerializedName("vehicle_type")
    val vehicle_type: String?,
)