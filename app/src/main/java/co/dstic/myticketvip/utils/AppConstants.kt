package co.dstic.myticketvip.utils

class AppConstants {

    companion object {
        //API URL
        const val apiURL = "https://api-production-cvcar.tk/api/v1/"
        //const val apiURL = "http://192.168.0.7:3000/api/v1/"
        //API MISDATOS
        const val apiMisDatosUrl = "https://api.misdatos.com.co/api/"
        const val apiMisDatosKey = "yzi53rgccgqsc8fzx2xblmhoyfx60zmskd0ue5ywx9rwiy2q"
        //AWS
        const val awsAccessKey = "AKIAIBWRS65GJG3NKB5Q"
        const val awsSecretKey = "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"
        //MAIN URL
        const val mainURL = "https://cvcar.com.co/"
        //Notification Codes
        const val notificationChannelId = "001"
        const val requestCodeLocation = 101
        const val requestCodeCamera = 102
        //Preferences Keys
        const val userId = "userId"
        const val userUid = "userUid"
        const val userCid = "userCid"
        const val userName = "userName"
        const val userEmail = "userEmail"
        const val userPhone = "userPhone"
        const val userAddress = "userAddress"
        //Database Name
        const val dbName = "cvcar"
        //Table Names
        const val tblPartner = "partner"
        const val tblVehicle = "vehicles"
        const val tblVehicleCv = "cv"
    }
}