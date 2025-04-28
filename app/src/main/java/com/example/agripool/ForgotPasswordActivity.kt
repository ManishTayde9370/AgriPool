package com.example.agripool

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)


        tvBackToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSubmit.setOnClickListener {
            val username = etUsername.text.toString()

            if (username.isNotEmpty()) {

                Toast.makeText(this, "Password reset link sent to your email.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
