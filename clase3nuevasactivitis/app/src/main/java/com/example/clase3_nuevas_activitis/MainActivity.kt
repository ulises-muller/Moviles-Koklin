package com.example.clase3_nuevas_activitis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var et1: EditText
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        et1 = findViewById(R.id.et1)
        btn = findViewById(R.id.btn)

        btn.setOnClickListener {
            val text1 = et1.text.toString()

            intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("text1", text1)
            startActivity(intent)
        }
    }
}