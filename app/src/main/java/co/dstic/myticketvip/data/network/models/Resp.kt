package co.dstic.myticketvip.data.network.models

import androidx.annotation.Keep
import co.dstic.myticketvip.data.network.models.runt.vehicle.Data
import com.google.gson.annotations.SerializedName

@Keep
data class Resp(
    val statusCode: Int,
    val data: Data,
    @SerializedName("users")
    val users: List<User>,
    @SerializedName("user")
    val user: User,
    @SerializedName("partners")
    val partners: List<Partner>,
    @SerializedName("partner")
    val partner: Partner,
    @SerializedName("vehicles")
    val vehicles: List<Vehicle>,
    @SerializedName("companies")
    val companies: List<String>,
    @SerializedName("vehicle")
    val vehicle: Vehicle,
    @SerializedName("document")
    val document: Cv,
    @SerializedName("kms")
    val kms: List<Cv>,
    @SerializedName("fuels")
    val fuels: List<Cv>,
    @SerializedName("oils")
    val oils: List<Cv>,
)