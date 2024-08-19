package com.example.moviles2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
        lateinit var nombre : String
        val numero = 3
        lateinit var button: Button
        lateinit var textView: TextView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.buttonMain)
        textView = findViewById(R.id.textViewMain)
        textView.text = "Hola Mundo"
        button.setOnClickListener {
            Toast.makeText(this, "se presiono el boton", Toast.LENGTH_SHORT).show()
        }

        //repaso abajo
        nombre = "lucas"
        llamarImpresion("caca")
        val resultado = calcularArea(numero, 3, 2)
        llamarImpresion(resultado.toString())
        val valores : Valores = Valores(1,3)
        val valores2 : Valores = Valores(2,4)
        val resultado2 = valores.calcularSuma()
        val nuevosValores : ValoresClass = ValoresClass()
        nuevosValores.numero1 = 5
        nuevosValores.numero2 = 7





    }

    private fun calcularArea(i: Int, i1: Int, i2: Int): Int {
        return i*i1/i2
    }

    private fun llamarImpresion(text : String) {
        println(nombre)
    }

    private fun imprimirNumero() {
        println(numero)
    }
}

