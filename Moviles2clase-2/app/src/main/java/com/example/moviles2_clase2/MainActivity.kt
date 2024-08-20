package com.example.moviles2_clase2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        enter.setOnClickListener {

            if (et1.text != null){
                var num1 : Int = et1.text.toString().toInt()
            } else {
                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
            if (et2.text != null){
                var num2 : Int = et2.text.toString().toInt()
            } else {
                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
            }
            }

            val num1 : Int = et1.text.toString().toInt()
            val num2 : Int = et2.text.toString().toInt()
            val suma = num1 + num2
            txView.text = suma.toString()
        }
    }
}
