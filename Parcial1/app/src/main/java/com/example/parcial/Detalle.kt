package com.example.parcial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Detalle : AppCompatActivity() {
    private lateinit var nombre : TextView
    private lateinit var deporte : TextView
    private lateinit var button : Button
    private lateinit var deportista : Deportista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        nombre = findViewById(R.id.Nombre)
        deporte = findViewById(R.id.Deporte)
        button = findViewById(R.id.button)

        val bundle = intent.extras

        deportista = Deportista(bundle?.getString("nombre").toString(), bundle?.getString("deporte").toString())
        deporte.text = deportista.nombre
        nombre.text = deportista.deporte
        button.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}