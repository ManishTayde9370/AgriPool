package com.example.agripool

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class RequestTransportActivity : AppCompatActivity() {

    private lateinit var etGoodsType: EditText
    private lateinit var etQuantity: EditText
    private lateinit var etPickupLocation: EditText
    private lateinit var etDeliveryLocation: EditText

    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var btnSubmitRequest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_transport)

        etGoodsType = findViewById(R.id.etGoodsType)
        etQuantity = findViewById(R.id.etQuantity)
        etPickupLocation = findViewById(R.id.etPickupLocation)
        etDeliveryLocation = findViewById(R.id.etDeliveryLocation)
        etDate = findViewById(R.id.etDate)
        etTime = findViewById(R.id.etTime)
        btnSubmitRequest = findViewById(R.id.btnSubmitRequest)

        // 📅 Date Picker
        etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, day ->
                    val formattedDate = "$day/${month + 1}/$year"
                    etDate.setText(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // ⏰ Time Picker
        etTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    val formattedTime = String.format("%02d:%02d", hourOfDay, minute)
                    etTime.setText(formattedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }

        btnSubmitRequest.setOnClickListener {
            val goodsType = etGoodsType.text.toString()
            val quantity = etQuantity.text.toString()
            val pickupLocation = etPickupLocation.text.toString()
            val deliveryLocation = etDeliveryLocation.text.toString()
            val preferredDate = etDate.text.toString()
            val preferredTime = etTime.text.toString()

            if (goodsType.isNotEmpty() && quantity.isNotEmpty() && pickupLocation.isNotEmpty()
                && deliveryLocation.isNotEmpty() && preferredDate.isNotEmpty() && preferredTime.isNotEmpty()
            ) {
                saveRequestToLocal(goodsType, quantity, pickupLocation, deliveryLocation, preferredDate, preferredTime)
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveRequestToLocal(
        goodsType: String,
        quantity: String,
        pickupLocation: String,
        deliveryLocation: String,
        preferredDate: String,
        preferredTime: String
    ) {
        val sharedPrefs = getSharedPreferences("TransportRequests", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        val existingRequests = sharedPrefs.getString("requests", "[]")
        val requestArray = JSONArray(existingRequests)

        val newRequest = JSONObject()
        newRequest.put("goodsType", goodsType)
        newRequest.put("quantity", quantity)
        newRequest.put("pickupLocation", pickupLocation)
        newRequest.put("deliveryLocation", deliveryLocation)
        newRequest.put("preferredDate", preferredDate)
        newRequest.put("preferredTime", preferredTime)

        requestArray.put(newRequest)
        editor.putString("requests", requestArray.toString())
        editor.apply()

        Toast.makeText(this, "Transport request submitted", Toast.LENGTH_SHORT).show()
        finish()
    }
}
