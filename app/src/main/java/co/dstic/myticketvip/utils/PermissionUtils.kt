package co.dstic.myticketvip.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import co.dstic.myticketvip.MyApp

object PermissionUtils {

    fun checkLocationEnabled(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lm = MyApp.context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            lm.isLocationEnabled
        } else {
            val mode = Settings.Secure.getInt(MyApp.context.contentResolver, Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF)
            mode != Settings.Secure.LOCATION_MODE_OFF
        }
    }

    fun checkLocationPermission(act: Activity?): Boolean {
        return if (ContextCompat.checkSelfPermission(MyApp.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(act!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), AppConstants.requestCodeLocation)
            false
        } else {
            true
        }
    }

    fun checkCameraPermission(act: Activity?): Boolean {
        return if (ContextCompat.checkSelfPermission(MyApp.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(act!!, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), AppConstants.requestCodeCamera)
            false
        } else {
            true
        }
    }
}
