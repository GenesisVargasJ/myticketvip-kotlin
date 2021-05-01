package co.dstic.myticketvip.data.network;

import co.dstic.myticketvip.data.network.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    /* USERS */
    @GET("users/{id}")
    fun getUser(@Path("id") id: String): Call<Resp>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: String, @Body user: User): Call<User>

    /* VEHICLES */
    @GET("users/{id}/vehicles")
    fun getVehicles(@Path("id") id: String): Call<Resp>

    @GET("vehicles/{id}")
    fun getVehicle(@Path("id") id: String): Call<Resp>

    @GET("vehicles/{id}/history")
    fun getVehicleHistory(@Path("id") id: String): Call<Resp>

    @POST("vehicles")
    fun createVehicle(@Body vehicle: Vehicle): Call<Vehicle>

    @DELETE("vehicles/{id}")
    fun deleteVehicle(@Path("id") id: String): Call<Resp>

    /* VEHICLE CV */
    @GET("vehicles/{id}/{type}")
    fun getVehicleCv(@Path("id") id: String, @Path("type") type: String): Call<Resp>

    @POST("vehicles/{id}/{type}")
    fun createVehicleCv(@Path("id") id: String, @Path("type") type: String, @Body soat: Cv): Call<Cv>

    @PUT("vehicles/{id}/{type}/{typeid}")
    fun updateVehicleCv(@Path("id") id: String, @Path("type") type: String, @Path("typeid") typeid: String, @Body soat: Cv): Call<Cv>

    /* PARTNERS */
    @GET("partners/{id}")
    fun getPartner(@Path("id") id: String): Call<Resp>

    @GET("partners/type/{type}")
    fun getPartnersByType(@Path("type") type: String): Call<Resp>

    @GET("partners/city/88/location/{lat}/{lng}")
    fun getPartnersByLocation(@Path("lat") lat: String, @Path("lng") lng: String): Call<Resp>

    @GET("partners/city/88/type/{type}")
    fun getPartnersByCityType(@Path("type") type: Int): Call<Resp>

    /* RUNT */
    @FormUrlEncoded
    @POST("co/runt/consultarVehiculo")
    fun getVehicleRunt(@Field("documentType") documentType: String, @Field("documentNumber") documentNumber: String, @Field("vehicle") vehicle: String): Call<Resp>
}