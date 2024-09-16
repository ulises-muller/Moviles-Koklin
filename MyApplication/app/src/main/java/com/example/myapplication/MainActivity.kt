package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var edad: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        button = findViewById(R.id.button)
        nombre = findViewById(R.id.nombreget)
        apellido = findViewById(R.id.apellidoget)
        edad = findViewById(R.id.edadget)

        button.setOnClickListener {
            if (!nombre.text.isBlank() && !apellido.text.isBlank() && !edad.text.isBlank()) {
                val datos = Datos(nombre.text.toString(), apellido.text.toString(), edad.text.toString())
                intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("nombre", datos.nombre)
                intent.putExtra("apellido", datos.apellido)
                intent.putExtra("edad", datos.edad)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}