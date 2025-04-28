package com.example.agripool

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etAddress = findViewById<EditText>(R.id.etAddress)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val address = etAddress.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() &&
                phone.isNotEmpty() && address.isNotEmpty()) {

                // Store user data in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.putString("email", email)
                editor.putString("phone", phone)
                editor.putString("address", address)
                editor.apply()

                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                val loginIntent = Intent(this, LoginActivity::class.java)
                loginIntent.putExtra("username", username)
                loginIntent.putExtra("password", password)
                startActivity(loginIntent)
                finish()
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
