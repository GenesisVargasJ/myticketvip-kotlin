package co.dstic.myticketvip.data.db

import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import co.dstic.myticketvip.data.network.models.Cv
import co.dstic.myticketvip.data.network.models.Partner
import co.dstic.myticketvip.data.network.models.Vehicle
import co.dstic.myticketvip.utils.AppConstants
import java.util.*

class AppDbHelper {

    private var cr: Cursor? = null
    private val db = DbHelper()

    /* Generic */

    fun isEmptyTable(table: String): Boolean {
        db.connectDb()
        cr = db.getData("SELECT EXISTS(SELECT 1 FROM $table)")
        cr!!.moveToFirst()
        val set: Boolean = cr!!.getInt(0) != 1
        cr!!.close()
        db.disconnectDb()
        return set
    }

    fun existData(table: String, where: String): Boolean {
        db.connectDb()
        cr = db.getData("SELECT COUNT(*) FROM $table WHERE $where")
        cr!!.moveToFirst()
        val set: Boolean = cr!!.getInt(0) > 0
        cr!!.close()
        db.disconnectDb()
        return set
    }

    fun existDataArray(table: String, field: String, value: String): Boolean {
        var set = false
        db.connectDb()
        val sql = "SELECT * FROM $table"
        val cr = db.getData(sql)
        if (cr.moveToFirst()) {
            do {
                val idPartnerType = cr.getString(cr.getColumnIndex("id_partner_type")).split(",")
                if (idPartnerType.contains(value)) {
                    set = true
                    break
                }
            } while (cr.moveToNext())
        }
        cr.close()
        db.disconnectDb()
        return set
    }

    fun deleteTables() {
        db.connectDb()
        db.delete(AppConstants.tblVehicle)
        db.delete(AppConstants.tblVehicleCv)
        db.delete(AppConstants.tblPartner)
        db.disconnectDb()
    }

    /* CREATE */

    fun registerVehicle(vehicle: Vehicle) {
        db.connectDb()
        val value = ContentValues()
        value.put("id", vehicle.id)
        value.put("id_user", vehicle.id_user)
        value.put("plate", vehicle.plate)
        value.put("brand", vehicle.brand)
        value.put("line", vehicle.line)
        value.put("model", vehicle.model)
        value.put("vehicle_type", vehicle.vehicle_type)
        value.put("id_vehicle_type", vehicle.id_vehicle_type)
        value.put("fuel_type", vehicle.fuel_type)
        value.put("service_type", vehicle.service_type)
        value.put("chassis_number", vehicle.chassis_number)
        value.put("color", vehicle.color)
        value.put("motor_number", vehicle.motor_number)
        value.put("cylinder_capacity", vehicle.cylinder_capacity)
        value.put("city", vehicle.city)
        value.put("cid", vehicle.cid)
        value.put("ctype", vehicle.ctype)
        db.insertData(AppConstants.tblVehicle, value)
        db.disconnectDb()
    }

    fun registerVehicleCv(cv: Cv, type: String) {
        db.connectDb()
        val value = ContentValues()
        value.put("id", cv.id)
        value.put("id_vehicle", cv.id_vehicle)
        value.put("id_partner", cv.id_partner)
        value.put("km", cv.km)
        value.put("number", cv.number)
        value.put("date", cv.date)
        value.put("date_expedition", cv.date_expedition)
        value.put("date_expire", cv.date_expire)
        value.put("url_image", cv.url_image)
        value.put("type", type)
        db.insertData(AppConstants.tblVehicleCv, value)
        db.disconnectDb()
    }

    fun registerPartner(partner: Partner) {
        val separator = ","
        db.connectDb()
        val value = ContentValues()
        value.put("id", partner.id)
        value.put("uid", partner.uid)
        value.put("cid", partner.cid)
        value.put("name", partner.name)
        value.put("name_person", partner.name_person)
        value.put("phone", partner.phone)
        value.put("email", partner.email)
        value.put("address", partner.address)
        value.put("coordinates", partner.coordinates)
        value.put("id_partner_type", java.lang.String.join(separator, partner.id_partner_type))
        value.put("partner_type", partner.partner_type)
        value.put("schedule", partner.schedule)
        value.put("schedule_start", partner.schedule_start)
        value.put("schedule_end", partner.schedule_end)
        value.put("id_city", partner.id_city)
        value.put("city", partner.city)
        value.put("brands", partner.brands)
        value.put("id_vehicle_type", java.lang.String.join(separator, partner.id_vehicle_type))
        value.put("vehicle_type", partner.vehicle_type)
        db.insertData(AppConstants.tblPartner, value)
        db.disconnectDb()
    }

    /* READ */

    fun getVehicles(): List<Vehicle> {
        val vehicles: MutableList<Vehicle> = ArrayList<Vehicle>()
        db.connectDb()
        val table = AppConstants.tblVehicle
        val sql = "SELECT * FROM $table"
        val cr = db.getData(sql)
        if (cr.moveToFirst()) {
            do {
                val id = cr.getString(cr.getColumnIndex("id"))
                val idUser = cr.getString(cr.getColumnIndex("id_user"))
                val plate = cr.getString(cr.getColumnIndex("plate"))
                val brand = cr.getString(cr.getColumnIndex("brand"))
                val line = cr.getString(cr.getColumnIndex("line"))
                val model = cr.getString(cr.getColumnIndex("model"))
                val vehicleType = cr.getString(cr.getColumnIndex("vehicle_type"))
                val idVehicleType = cr.getString(cr.getColumnIndex("id_vehicle_type"))
                val fuelType = cr.getString(cr.getColumnIndex("fuel_type"))
                val serviceType = cr.getString(cr.getColumnIndex("service_type"))
                val chassisNumber = cr.getString(cr.getColumnIndex("chassis_number"))
                val color = cr.getString(cr.getColumnIndex("color"))
                val motorNumber = cr.getString(cr.getColumnIndex("motor_number"))
                val cylinderCapacity = cr.getString(cr.getColumnIndex("cylinder_capacity"))
                val city = cr.getString(cr.getColumnIndex("city"))
                val cid = cr.getString(cr.getColumnIndex("cid"))
                val ctype = cr.getString(cr.getColumnIndex("ctype"))
                vehicles.add(Vehicle(id, idUser, plate, brand, line, model, vehicleType, idVehicleType, fuelType, serviceType, chassisNumber, color, motorNumber, cylinderCapacity, city, cid, ctype))
            } while (cr.moveToNext())
        }
        cr.close()
        db.disconnectDb()
        return vehicles
    }

    fun getVehicle(id_vehicle: String): Vehicle {
        lateinit var vehicle: Vehicle
        db.connectDb()
        val table = AppConstants.tblVehicle
        val sql = "SELECT * FROM $table WHERE id_vehicle = '$id_vehicle'"
        val cr = db.getData(sql)
        if(cr.count > 0) {
            if (cr.moveToFirst()) {
                val id = cr.getString(cr.getColumnIndex("id"))
                val idUser = cr.getString(cr.getColumnIndex("id_user"))
                val plate = cr.getString(cr.getColumnIndex("plate"))
                val brand = cr.getString(cr.getColumnIndex("brand"))
                val line = cr.getString(cr.getColumnIndex("line"))
                val model = cr.getString(cr.getColumnIndex("model"))
                val vehicleType = cr.getString(cr.getColumnIndex("vehicle_type"))
                val idVehicleType = cr.getString(cr.getColumnIndex("id_vehicle_type"))
                val fuelType = cr.getString(cr.getColumnIndex("fuel_type"))
                val serviceType = cr.getString(cr.getColumnIndex("service_type"))
                val chassisNumber = cr.getString(cr.getColumnIndex("chassis_number"))
                val color = cr.getString(cr.getColumnIndex("color"))
                val motorNumber = cr.getString(cr.getColumnIndex("motor_number"))
                val cylinderCapacity = cr.getString(cr.getColumnIndex("cylinder_capacity"))
                val city = cr.getString(cr.getColumnIndex("city"))
                val cid = cr.getString(cr.getColumnIndex("cid"))
                val ctype = cr.getString(cr.getColumnIndex("ctype"))
                vehicle = Vehicle(id, idUser, plate, brand, line, model, vehicleType, idVehicleType, fuelType, serviceType, chassisNumber, color, motorNumber, cylinderCapacity, city, cid, ctype)
            }
        } else {
            vehicle = Vehicle("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        }
        cr.close()
        db.disconnectDb()
        return vehicle
    }

    fun getVehicleCv(id_vehicle: String, type: String): Cv? {
        var cv: Cv? = null
        db.connectDb()
        val table = AppConstants.tblVehicleCv
        val sql = "SELECT * FROM $table WHERE id_vehicle = '$id_vehicle' AND type = '$type'"
        val cr = db.getData(sql)
        if(cr.count > 0) {
            if (cr.moveToFirst()) {
                val id = cr.getString(cr.getColumnIndex("id"))
                val number = cr.getString(cr.getColumnIndex("number"))
                val km = cr.getString(cr.getColumnIndex("km"))
                val date = cr.getString(cr.getColumnIndex("date"))
                val dateExpedition = cr.getString(cr.getColumnIndex("date_expedition"))
                val dateExpire = cr.getString(cr.getColumnIndex("date_expire"))
                val idPartner = cr.getString(cr.getColumnIndex("id_partner"))
                val urlImage = cr.getString(cr.getColumnIndex("url_image"))
                cv = Cv(id, id_vehicle, number, km, date, dateExpedition, dateExpire, idPartner, urlImage)
            }
        }
        cr.close()
        db.disconnectDb()
        return cv
    }

    fun getPartners(type: Int): List<Partner> {
        val partners: MutableList<Partner> = ArrayList<Partner>()
        db.connectDb()
        val table = AppConstants.tblPartner
        val sql = "SELECT * FROM $table"
        val cr = db.getData(sql)
        if (cr.moveToFirst()) {
            do {
                val id = cr.getString(cr.getColumnIndex("id"))
                val uid = cr.getString(cr.getColumnIndex("uid"))
                val cid = cr.getString(cr.getColumnIndex("cid"))
                val name = cr.getString(cr.getColumnIndex("name"))
                val namePerson = cr.getString(cr.getColumnIndex("name_person"))
                val phone = cr.getString(cr.getColumnIndex("phone"))
                val email = cr.getString(cr.getColumnIndex("email"))
                val address = cr.getString(cr.getColumnIndex("address"))
                val coordinates = cr.getString(cr.getColumnIndex("coordinates"))
                val idPartnerType = cr.getString(cr.getColumnIndex("id_partner_type")).split(",")
                val partnerType = cr.getString(cr.getColumnIndex("partner_type"))
                val schedule = cr.getString(cr.getColumnIndex("schedule"))
                val scheduleStart = cr.getString(cr.getColumnIndex("schedule_start"))
                val scheduleEnd = cr.getString(cr.getColumnIndex("schedule_end"))
                val idCity = cr.getString(cr.getColumnIndex("id_city"))
                val city = cr.getString(cr.getColumnIndex("city"))
                val brands = cr.getString(cr.getColumnIndex("brands"))
                val idVehicleType = cr.getString(cr.getColumnIndex("id_vehicle_type")).split(",")
                val vehicleType = cr.getString(cr.getColumnIndex("vehicle_type"))
                Log.e("cvcar", "arr: " + idPartnerType)
                if (idPartnerType.contains(type.toString())) {
                    partners.add(Partner(id, uid, cid, name, namePerson, phone, email, address, coordinates, idPartnerType, partnerType, schedule, scheduleStart, scheduleEnd, idCity, city, brands, idVehicleType, vehicleType))
                }
            } while (cr.moveToNext())
        }
        cr.close()
        db.disconnectDb()
        return partners
    }

    /* UPDATE */

    fun updateVehicle(vehicle: Vehicle) {
        db.connectDb()
        val value = ContentValues()
        value.put("id", vehicle.id)
        value.put("id_user", vehicle.id_user)
        value.put("plate", vehicle.plate)
        value.put("brand", vehicle.brand)
        value.put("line", vehicle.line)
        value.put("model", vehicle.model)
        value.put("vehicle_type", vehicle.vehicle_type)
        value.put("id_vehicle_type", vehicle.id_vehicle_type)
        value.put("fuel_type", vehicle.fuel_type)
        value.put("service_type", vehicle.service_type)
        value.put("chassis_number", vehicle.chassis_number)
        value.put("color", vehicle.color)
        value.put("motor_number", vehicle.motor_number)
        value.put("cylinder_capacity", vehicle.cylinder_capacity)
        value.put("city", vehicle.city)
        value.put("cid", vehicle.cid)
        value.put("ctype", vehicle.ctype)
        val id = vehicle.id
        db.updateData(AppConstants.tblVehicle, "id = $id", value)
        db.disconnectDb()
    }

    fun updateVehicleCv(cv: Cv, type: String) {
        db.connectDb()
        val value = ContentValues()
        value.put("id", cv.id)
        value.put("id_vehicle", cv.id_vehicle)
        value.put("id_partner", cv.id_partner)
        value.put("km", cv.km)
        value.put("number", cv.number)
        value.put("date", cv.date)
        value.put("date_expedition", cv.date_expedition)
        value.put("date_expire", cv.date_expire)
        value.put("url_image", cv.url_image)
        value.put("type", type)
        val id = cv.id
        db.updateData(AppConstants.tblVehicleCv, "id = $id", value)
        db.disconnectDb()
    }

    /* DELETE */

    fun deleteVehicle(id: String) {
        db.connectDb()
        db.delete(AppConstants.tblVehicle, "id", id)
        db.disconnectDb()
    }
}
