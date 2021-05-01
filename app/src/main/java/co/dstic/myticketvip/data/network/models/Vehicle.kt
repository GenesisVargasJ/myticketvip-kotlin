package co.dstic.myticketvip.data.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Vehicle(
    @SerializedName("id")
    var id: String?,
    @SerializedName("id_user")
    val id_user: String?,
    @SerializedName("plate")
    val plate: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("line")
    val line: String?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("vehicle_type")
    val vehicle_type: String?,
    @SerializedName("id_vehicle_type")
    val id_vehicle_type: String?,
    @SerializedName("fuel_type")
    val fuel_type: String?,
    @SerializedName("service_type")
    val service_type: String?,
    @SerializedName("chassis_number")
    val chassis_number: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("motor_number")
    val motor_number: String?,
    @SerializedName("cylinder_capacity")
    val cylinder_capacity: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("cid")
    val cid: String?,
    @SerializedName("ctype")
    val ctype: String?,
)