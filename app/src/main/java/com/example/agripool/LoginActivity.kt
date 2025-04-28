package com.example.agripool

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val passedUsername = intent.getStringExtra("username") ?: ""
        val passedPassword = intent.getStringExtra("password") ?: ""

        etUsername.setText(passedUsername)
        etPassword.setText(passedPassword)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val enteredUsername = etUsername.text.toString().trim()
            val enteredPassword = etPassword.text.toString().trim()

            if (enteredUsername.isNotEmpty() && enteredPassword.isNotEmpty()) {
                val savedUsername = sharedPreferences.getString("username", null)
                val savedPassword = sharedPreferences.getString("password", null)

                if (savedUsername == enteredUsername) {
                    if (savedPassword == enteredPassword) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
