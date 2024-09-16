package com.example.moviles2_clase2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity :       AppCompatActivity() {
    private lateinit var et1 : EditText
    private lateinit var et2 : EditText
    private lateinit var txView: TextView
    private lateinit var enter : Button
    private lateinit var getOperacion : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        enter = findViewById(R.id.enter)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        txView = findViewById(R.id.txView)
        getOperacion = findViewById(R.id.operacion)

        enter.setOnClickListener {
            if (et1.text.isBlank() || et2.text.isBlank() || getOperacion.text.isBlank()) {
                Toast.makeText(this, "Agrega alguno numero viste", Toast.LENGTH_SHORT).show()
            } else {
                var resultado = Calcular(et1.text.toString().toDouble(), et2.text.toString().toDouble(), getOperacion.text.toString())
                txView.text = resultado.toString()
            }
        }
    }
    private fun Calcular(a: Double, b: Double, operacion : String) : Double {
        return when(operacion){
            "suma" -> a + b
            "resta" -> a - b
            "multiplicacion" -> a * b
            "division" -> a / b
            else -> throw IllegalArgumentException("Operacion no valida")
        }
    }
}
