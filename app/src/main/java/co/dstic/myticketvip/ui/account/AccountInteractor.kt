package co.dstic.myticketvip.ui.account

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import co.dstic.myticketvip.R
import co.dstic.myticketvip.MyApp
import co.dstic.myticketvip.data.network.ApiClient
import co.dstic.myticketvip.data.network.ApiService
import co.dstic.myticketvip.data.network.models.Resp
import co.dstic.myticketvip.data.network.models.User
import co.dstic.myticketvip.data.prefs.AppPreferencesHelper
import co.dstic.myticketvip.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountInteractor {

    private val appPreferencesHelper = AppPreferencesHelper()

    /* Global */
    interface AccountListener {
        fun onSuccess()
        fun onError(message: String)
    }

    private fun registerUser(name: String, phone: String, email: String, password: String, listener: AccountListener) {
        MyApp.getAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                MyApp.getAuth().currentUser!!.getIdToken(true).addOnSuccessListener { getTokenResult ->
                    val service: ApiService = ApiClient.getClient(getTokenResult.token!!).create(ApiService::class.java)
                    val user = User(null, MyApp.getAuth().currentUser!!.uid, "", name, phone, email, "")
                    val reqCall: Call<User> = service.createUser(user)
                    reqCall.enqueue(object : Callback<User> {
                        override fun onResponse(@NonNull call: Call<User>, @NonNull response: Response<User>) {
                            if (response.code() == 201) {
                                val data = response.body()!!
                                appPreferencesHelper.setId(data.id!!)
                                appPreferencesHelper.setUid(MyApp.getAuth().currentUser!!.uid)
                                appPreferencesHelper.setCid("")
                                appPreferencesHelper.setName(name)
                                appPreferencesHelper.setEmail(email)
                                appPreferencesHelper.setPhone(phone)
                                appPreferencesHelper.setAddress("")
                                listener.onSuccess()
                            } else {
                                MyApp.getAuth().signOut()
                                listener.onError(response.errorBody()!!.string())
                            }
                        }

                        override fun onFailure(@NonNull call: Call<User>, @NonNull t: Throwable) {
                            MyApp.getAuth().signOut()
                            listener.onError(t.message.toString())
                        }
                    })
                }
            } else {
                listener.onError(MyApp.context.getString(R.string.msgError))
            }
        }
    }

    private fun loginUser(email: String, password: String, listener: AccountListener) {
        MyApp.getAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                MyApp.getAuth().currentUser!!.getIdToken(true).addOnSuccessListener { getTokenResult ->
                    val service: ApiService = ApiClient.getClient(getTokenResult.token!!).create(ApiService::class.java)
                    val reqCall: Call<Resp> = service.getUser(MyApp.getAuth().currentUser!!.uid)
                    reqCall.enqueue(object : Callback<Resp> {
                        override fun onResponse(@NonNull call: Call<Resp>, @NonNull response: Response<Resp>) {
                            if (response.code() == 200) {
                                val user = response.body()!!.user
                                appPreferencesHelper.setId(user.id!!)
                                appPreferencesHelper.setUid(user.uid!!)
                                appPreferencesHelper.setCid(user.cid ?: "")
                                appPreferencesHelper.setName(user.name!!)
                                appPreferencesHelper.setEmail(user.email ?: "")
                                appPreferencesHelper.setPhone(user.phone ?: "")
                                appPreferencesHelper.setAddress(user.address ?: "")
                                listener.onSuccess()
                            } else {
                                MyApp.getAuth().signOut()
                                listener.onError(response.errorBody()!!.string())
                            }
                        }

                        override fun onFailure(@NonNull call: Call<Resp>, @NonNull t: Throwable) {
                            MyApp.getAuth().signOut()
                            listener.onError(t.message.toString())
                        }
                    })
                }
            } else {
                listener.onError(MyApp.context.getString(R.string.msgUserInvalid))
            }
        }
    }

    /* Login */
    interface LoginListener {
        fun onLogin()
        fun onLoginError(message: String)
    }

    fun login(email: String, password: String, listener: LoginListener) {
        if (email == "" || password == "") {
            listener.onLoginError(MyApp.context.getString(R.string.msgNoComplete))
        } else {
            if (NetworkUtils.isConnected && NetworkUtils.isInternetWorking) {
                loginUser(email, password, object : AccountListener {
                    override fun onSuccess() {
                        listener.onLogin()
                    }

                    override fun onError(message: String) {
                        listener.onLoginError(message)
                    }
                })
            } else {
                listener.onLoginError(MyApp.context.getString(R.string.msgNoInternet))
            }
        }
    }

    /* Register */
    interface RegisterListener {
        fun onRegister()
        fun onRegisterError(message: String)
    }

    fun register(name: String, phone: String, email: String, password: String, listener: RegisterListener) {
        if (email == "" || password == "" ) {
            listener.onRegisterError(MyApp.context.getString(R.string.msgNoComplete))
        } else {
            if (NetworkUtils.isConnected && NetworkUtils.isInternetWorking) {
                registerUser(name, phone, email, password, object : AccountListener {
                    override fun onSuccess() {
                        listener.onRegister()
                    }

                    override fun onError(message: String) {
                        listener.onRegisterError(message)
                    }
                })
            } else {
                listener.onRegisterError(MyApp.context.getString(R.string.msgNoInternet))
            }
        }
    }

    /* Recover */
    interface RecoverListener {
        fun onRecover()
        fun onRecoverError(message: String)
    }

    fun recover(email: String, listener: RecoverListener) {
        if (email == "") {
            listener.onRecoverError(MyApp.context.getString(R.string.msgNoComplete))
        } else {
            if (NetworkUtils.isConnected && NetworkUtils.isInternetWorking) {
                MyApp.getAuth().sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        listener.onRecover()
                    } else {
                        listener.onRecoverError(MyApp.context.getString(R.string.msgUserExist))
                    }
                }
            } else {
                listener.onRecoverError(MyApp.context.getString(R.string.msgNoInternet))
            }
        }
    }

    /* Profile */
    interface ProfileListener {
        fun onProfileLoaded(cid: String, name: String, email: String, phone: String, address: String)
    }

    fun loadProfile(listener: ProfileListener) {
        listener.onProfileLoaded(appPreferencesHelper.getCid(), appPreferencesHelper.getName(), appPreferencesHelper.getEmail(), appPreferencesHelper.getPhone(), appPreferencesHelper.getAddress())
    }
}