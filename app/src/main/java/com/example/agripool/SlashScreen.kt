package com.example.agripool

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class SlashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_slash_screen)


        Handler(Looper.getMainLooper()).postDelayed({

            val i = Intent(this@SlashScreen, WelcomeActivity::class.java
            )
            startActivity(i)
            finish()
        }, 2000)

    }
}