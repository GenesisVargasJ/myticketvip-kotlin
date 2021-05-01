package co.dstic.myticketvip

import android.app.Application
import android.content.Context
import co.dstic.myticketvip.data.db.AppDbHelper
import com.google.firebase.auth.FirebaseAuth

class MyApp : Application() {

    override  fun onCreate() {
        instance = this
        auth = FirebaseAuth.getInstance();
        appDbHelper = AppDbHelper()
        super.onCreate()
    }

    companion object {

        private lateinit var instance: MyApp
        private lateinit var auth: FirebaseAuth
        private lateinit var appDbHelper: AppDbHelper

        val context : Context get() {
            return instance
        }

        fun getAuth(): FirebaseAuth {
            return auth
        }

        fun getAppDbHelper(): AppDbHelper {
            return appDbHelper
        }
    }
}