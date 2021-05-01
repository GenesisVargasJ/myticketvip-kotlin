package co.dstic.myticketvip.data.prefs

interface PreferencesHelper {

    fun setId(id: String)
    fun getId(): String
    fun setUid(uid: String)
    fun getUid(): String
    fun setCid(cid: String)
    fun getCid(): String
    fun setName(name: String)
    fun getName(): String
    fun setEmail(email: String)
    fun getEmail(): String
    fun setPhone(phone: String)
    fun getPhone(): String
    fun setAddress(address: String)
    fun getAddress(): String
    fun deleteUser()
}