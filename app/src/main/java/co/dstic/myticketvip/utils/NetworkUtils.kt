package co.dstic.myticketvip.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.StrictMode
import co.dstic.myticketvip.MyApp
import java.net.HttpURLConnection
import java.net.URL

class NetworkUtils {

    companion object {

        // Check if there is any connectivity
        val isConnected : Boolean get() {
            val cm = MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo!=null && networkInfo.isConnected
        }

        //Check if the connection has internet
        val isInternetWorking: Boolean get() {
            var success = false
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            try {
                val url = URL("https://www.google.com/")
                val connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 5000
                connection.connect()
                success = connection.responseCode == 200
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return success
        }
    }
}