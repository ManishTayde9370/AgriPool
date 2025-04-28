package com.example.agripool

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ContactUsActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        title = "Contact Us"

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMessage = findViewById(R.id.etMessage)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvEmail = findViewById(R.id.tvEmail)
        tvPhone = findViewById(R.id.tvPhone)

        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val message = etMessage.text.toString()

            if (name.isBlank() || email.isBlank() || message.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("support@agripool.in"))
                    putExtra(Intent.EXTRA_SUBJECT, "Contact from $name")
                    putExtra(Intent.EXTRA_TEXT, "Name: $name\nEmail: $email\n\n$message")
                }
                startActivity(Intent.createChooser(intent, "Send Email"))
            }
        }

        tvEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:support@agripool.in"))
            startActivity(Intent.createChooser(emailIntent, "Send Email"))
        }

        tvPhone.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919309183127"))
            startActivity(dialIntent)
        }
    }
}
