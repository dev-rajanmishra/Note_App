package com.rajanmishracse.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.content.Intent
import android.os.Handler
import android.os.Looper


class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000)
    }
}