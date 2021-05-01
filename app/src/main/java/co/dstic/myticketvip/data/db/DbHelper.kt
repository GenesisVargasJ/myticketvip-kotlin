package co.dstic.myticketvip.data.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

internal class DbHelper() {

    private val dbOpenHelper = DbOpenHelper()
    private var db: SQLiteDatabase? = null

    fun connectDb() {
        db = dbOpenHelper.writableDatabase
    }

    fun beginTransaction() {
        db!!.beginTransaction()
    }

    fun setTransactionSuccessful() {
        db!!.setTransactionSuccessful()
    }

    fun endTransaction() {
        db!!.endTransaction()
    }

    fun disconnectDb() {
        db!!.close()
    }

    fun delete(table: String) {
        db!!.execSQL("delete from $table")
    }

    fun delete(table: String, field: String, value: String) {
        db!!.execSQL("delete from $table where $field = '$value'")
    }

    fun delete(table: String, field: String, value: String, field1: String, value1: String) {
        db!!.execSQL("delete from $table where $field = '$value' and $field1 = '$value1'")
    }

    fun getData(sql: String?): Cursor {
        return db!!.rawQuery(sql, null)
    }

    fun insertData(table: String?, value: ContentValues?) {
        db!!.insert(table, null, value)
    }

    fun updateData(table: String?, where: String?, value: ContentValues?) {
        db!!.update(table, value, where, null)
    }
}
