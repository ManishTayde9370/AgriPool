package com.example.agripool

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvFullName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        title = "Profile"

        tvFullName = findViewById(R.id.tvFullName)
        tvUsername = findViewById(R.id.tvUsername)
        tvEmail = findViewById(R.id.tvEmail)

        btnLogout = findViewById(R.id.btnLogout)

        val sharedPrefs: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val fullName = sharedPrefs.getString("fullname", "User")
        val username = sharedPrefs.getString("username", "username123")
        val email = sharedPrefs.getString("email", "email@example.com")

        tvFullName.text = fullName
        tvUsername.text = "@$username"
        tvEmail.text = email


        btnLogout.setOnClickListener {
            sharedPrefs.edit().clear().apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
