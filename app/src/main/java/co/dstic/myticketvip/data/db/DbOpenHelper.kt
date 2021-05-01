package co.dstic.myticketvip.data.db

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import co.dstic.myticketvip.MyApp
import co.dstic.myticketvip.utils.AppConstants

internal class DbOpenHelper : SQLiteOpenHelper(MyApp.context, NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE1)
        db.execSQL(TABLE2)
        db.execSQL(TABLE3)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE " + AppConstants.tblVehicle)
        db.execSQL("DROP TABLE " + AppConstants.tblVehicleCv)
        db.execSQL("DROP TABLE " + AppConstants.tblPartner)
        onCreate(db)
    }

    companion object {
        private const val VERSION = 1
        private const val NAME: String = AppConstants.dbName
        private const val TABLE1 = "CREATE TABLE IF NOT EXISTS " + AppConstants.tblVehicle + "(id NUMERIC, id_user TEXT, plate TEXT, brand TEXT, line TEXT, model TEXT, vehicle_type TEXT, id_vehicle_type TEXT, fuel_type TEXT, service_type TEXT, chassis_number TEXT, color TEXT, motor_number TEXT, cylinder_capacity TEXT, city TEXT, cid TEXT, ctype TEXT)"
        private const val TABLE2 = "CREATE TABLE IF NOT EXISTS " + AppConstants.tblVehicleCv + "(id NUMERIC, id_vehicle NUMERIC, id_partner NUMERIC, km TEXT, number TEXT, date TEXT, date_expedition TEXT, date_expire TEXT, url_image TEXT, type TEXT)"
        private const val TABLE3 = "CREATE TABLE IF NOT EXISTS " + AppConstants.tblPartner + "(id NUMERIC, uid TEXT, cid TEXT, name TEXT, name_person TEXT, phone TEXT, email TEXT, address TEXT, coordinates TEXT, id_partner_type TEXT, partner_type TEXT, schedule TEXT, schedule_start TEXT, schedule_end TEXT, id_city TEXT, city TEXT, brands TEXT, id_vehicle_type TEXT, vehicle_type TEXT)"
    }
}