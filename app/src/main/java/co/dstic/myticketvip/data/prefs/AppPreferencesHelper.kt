package co.dstic.myticketvip.data.prefs

import android.content.Context
import android.content.SharedPreferences
import co.dstic.myticketvip.MyApp
import co.dstic.myticketvip.R
import co.dstic.myticketvip.utils.AppConstants

class AppPreferencesHelper : PreferencesHelper {

    private val prefs: SharedPreferences = MyApp.context.getSharedPreferences(MyApp.context.resources.getString(R.string.app_name), Context.MODE_PRIVATE)

    private fun read(key: String): String {
        return prefs.getString(key, "").toString()
    }

    private fun write(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(key, value)
            apply()
        }
    }

    private fun delete() {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            clear()
            apply()
        }
    }

    override fun setId(id: String) {
        write(AppConstants.userId,id)
    }

    override fun getId(): String {
        return read(AppConstants.userId)
    }

    override fun setUid(uid: String) {
        write(AppConstants.userUid,uid)
    }

    override fun getUid(): String {
        return read(AppConstants.userUid)
    }

    override fun setCid(cid: String) {
        write(AppConstants.userCid,cid)
    }

    override fun getCid(): String {
        return read(AppConstants.userCid)
    }

    override fun setName(name: String) {
        write(AppConstants.userName,name)
    }

    override fun getName(): String {
        return read(AppConstants.userName)
    }

    override fun setEmail(email: String) {
        write(AppConstants.userEmail,email)
    }

    override fun getEmail(): String {
        return read(AppConstants.userEmail)
    }

    override fun setPhone(phone: String) {
        write(AppConstants.userPhone,phone)
    }

    override fun getPhone(): String {
        return read(AppConstants.userPhone)
    }

    override fun setAddress(address: String) {
        write(AppConstants.userAddress,address)
    }

    override fun getAddress(): String {
        return read(AppConstants.userAddress)
    }

    override fun deleteUser() {
        delete()
    }
}