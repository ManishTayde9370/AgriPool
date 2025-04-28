package com.example.agripool

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class OfferTransportActivity : AppCompatActivity() {

    private lateinit var etVehicleType: EditText
    private lateinit var etLocation: EditText
    private lateinit var etAvailableTime: EditText
    private lateinit var etMaxLoad: EditText
    private lateinit var btnSubmitOffer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offer_transport)

        etVehicleType = findViewById(R.id.etVehicleType)
        etLocation = findViewById(R.id.etLocation)
        etAvailableTime = findViewById(R.id.etAvailableTime)
        etMaxLoad = findViewById(R.id.etMaxLoad)
        btnSubmitOffer = findViewById(R.id.btnSubmitOffer)

        // Time picker dialog for available time
        etAvailableTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                etAvailableTime.setText(formattedTime)
            }, hour, minute, true)

            timePicker.show()
        }

        btnSubmitOffer.setOnClickListener {
            val vehicleType = etVehicleType.text.toString()
            val location = etLocation.text.toString()
            val availableTime = etAvailableTime.text.toString()
            val maxLoad = etMaxLoad.text.toString()

            if (vehicleType.isNotEmpty() && location.isNotEmpty() &&
                availableTime.isNotEmpty() && maxLoad.isNotEmpty()) {

                saveOfferToLocalStorage(vehicleType, location, availableTime, maxLoad)
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveOfferToLocalStorage(
        vehicleType: String,
        location: String,
        availableTime: String,
        maxLoad: String
    ) {
        val sharedPreferences = getSharedPreferences("TransportOffers", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val existingOffers = sharedPreferences.getString("offers", "[]")
        val offerArray = JSONArray(existingOffers)

        val newOffer = JSONObject()
        newOffer.put("vehicleType", vehicleType)
        newOffer.put("location", location)
        newOffer.put("availableTime", availableTime)
        newOffer.put("maxLoad", maxLoad)

        offerArray.put(newOffer)
        editor.putString("offers", offerArray.toString())
        editor.apply()

        Toast.makeText(this, "Transport offer submitted", Toast.LENGTH_SHORT).show()
        finish()
    }
}
