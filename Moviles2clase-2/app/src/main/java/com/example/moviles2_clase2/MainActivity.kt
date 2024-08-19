package com.example.moviles2_clase2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var et1 : EditText
    private lateinit var et2 : EditText
    private lateinit var txView: TextView
    private lateinit var enter : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        enter = findViewById(R.id.enter)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        txView = findViewById(R.id.txView)


    }
}
