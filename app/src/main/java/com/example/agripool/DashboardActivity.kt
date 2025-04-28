package com.example.agripool

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView


class DashboardActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvWelcomeMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        Log.d("DashboardActivity", "Toolbar initialized: $toolbar")

        tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Guest")

        tvWelcomeMessage.text = "Hey, $username"

        val cardRequestTransport = findViewById<CardView>(R.id.cardRequestTransport)
        val cardOfferTransport = findViewById<CardView>(R.id.cardOfferTransport)

        cardRequestTransport.setOnClickListener {
            Log.d("DashboardActivity", "Request Transport card clicked")
            navigateTo(RequestTransportActivity::class.java)
        }

        cardOfferTransport.setOnClickListener {
            Log.d("DashboardActivity", "Offer Transport card clicked")
            navigateTo(OfferTransportActivity::class.java)
        }
    }

    private fun navigateTo(destination: Class<*>) {
        Log.d("DashboardActivity", "navigateTo() called, destination: ${destination.simpleName}")
        startActivity(Intent(this, destination))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                return true
            }


            R.id.menu_request -> {
                startActivity(Intent(this, RequestTransportActivity::class.java))
                return true
            }

            R.id.menu_offer -> {
                startActivity(Intent(this, OfferTransportActivity::class.java))
                return true
            }

            R.id.menu_contact -> {
                startActivity(Intent(this, ContactUsActivity::class.java))
                return true
            }

            R.id.menu_rate -> {
                startActivity(Intent(this, RateUsActivity::class.java))
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }
}