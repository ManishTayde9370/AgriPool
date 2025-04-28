package com.example.agripool

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RateUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_us)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitRating)

        btnSubmit.setOnClickListener {
            val rating = ratingBar.rating
            Toast.makeText(this, "Thanks for rating us $rating stars!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
