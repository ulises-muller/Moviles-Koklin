package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private lateinit var  nombre : TextView
    private lateinit var apellido : TextView
    private lateinit var edad : TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        nombre = findViewById(R.id.nombre)
        apellido = findViewById(R.id.apellido)
        edad = findViewById(R.id.edad)
        button = findViewById(R.id.button2)

        val datos = intent.extras
        val nombres = datos?.getString("nombre")?: ""
        val apellidos = datos?.getString("apellido")?: ""
        var edads = ""

        datos?.let{
            edads = it.getString("edad", "")
        }?: ""

        val getDatos = Datos(nombres, apellidos, edads)

        nombre.text = getDatos.nombre
        apellido.text = getDatos.apellido
        edad.text = getDatos.edad

        button.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            finish()
        }

        getDatos.imprimirUsuario()
    }
}