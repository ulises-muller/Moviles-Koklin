package com.example.clase4

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var tvCongrat: TextView
    private lateinit var tvSummary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tvCongrat = findViewById(R.id.tvCongrat)
        tvSummary = findViewById(R.id.tvSummary)

        val bundle = intent.extras

        val prod = bundle?.getString("product")
        val pm = bundle?.getString("price")

        tvSummary.text = "Pagaste $prod por medio de $pm"

    }
}