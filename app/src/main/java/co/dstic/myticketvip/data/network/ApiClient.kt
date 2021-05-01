package co.dstic.myticketvip.data.network;

import co.dstic.myticketvip.utils.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {
        fun getClient(token: String): Retrofit {
            val gson = GsonBuilder().serializeNulls().create()
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain: Interceptor.Chain ->
                val original: Request = chain.request()
                val requestBuilder: Request.Builder = original.newBuilder().header("Authorization","Bearer $token")
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }
            httpClient.connectTimeout(10, TimeUnit.SECONDS)
            httpClient.readTimeout(10, TimeUnit.SECONDS)
            val client = httpClient.build()
            return Retrofit.Builder()
                    .baseUrl(AppConstants.apiURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
        }

        fun getMisDatosClient(): Retrofit {
            val gson = GsonBuilder().serializeNulls().create()
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain: Interceptor.Chain ->
                val original: Request = chain.request()
                val requestBuilder: Request.Builder = original.newBuilder().header("Authorization", AppConstants.apiMisDatosKey)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }
            httpClient.connectTimeout(10, TimeUnit.SECONDS)
            httpClient.readTimeout(10, TimeUnit.SECONDS)
            val client = httpClient.build()
            return Retrofit.Builder()
                    .baseUrl(AppConstants.apiMisDatosUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
        }
    }
}