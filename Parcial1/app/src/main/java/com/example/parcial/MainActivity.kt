package com.example.parcial

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial1.R

class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var listView: ListView

    private lateinit var paises: String
    private lateinit var deportista: Deportista
    private lateinit var intentHelper: intentHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinnerPais)
        listView = findViewById(R.id.listEquipos)
        intentHelper = intentHelper()

        val equipo = mutableListOf("no cargo")

        val pais = listOf("Argentina","España","Estados Unidos")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pais)
        val adapterList = ArrayAdapter(this, android.R.layout.simple_list_item_1, equipo)

        spinner.adapter = adapter
        listView.adapter = adapterList


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                paises = pais[position]
                when (paises) {
                    "Argentina" -> {
                        equipo.clear()
                        equipo.addAll(listaNombres())
                        adapterList.notifyDataSetChanged()
                    }
                    "España" -> {
                        equipo.clear()
                        equipo.addAll(listaNombres())
                        adapterList.notifyDataSetChanged()
                    }
                    "Estados Unidos" -> {
                        equipo.clear()
                        equipo.addAll(listaNombres())
                        adapterList.notifyDataSetChanged()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        listView.setOnItemClickListener { parent, view, position, id ->
            val nombre = listaNombres()[position]
            val deporte = listaDeportes()[position]
            deportista = Deportista(nombre, deporte)
            intentHelper.intentDeportista(nombre, deporte, this)
        }

    }
    fun listaNombres(): List<String> {
        var nombres = when (paises){
            "Argentina" -> listOf(
                "Florencia Borelli", "Daiana Ocampo", "Elián Larregina", "Belén Casetta", "Nazareno Sasia", "Joaquín Gómez", "Lionel Messi", "Paula Pareto", "Santiago Lange", "Agustín Vernice")
            "España" -> listOf(
                "Carles Puyol","Adriana Cerezo","Maialen Chourraut","Ray Zapata: gimnasi","Andrés Iniesta","Damián Quintero","Saúl Craviotto","Marcus Cooper Walz","Carlos Arévalo","Rodrigo Germade")
            "Estados Unidos" -> listOf(
                "Tom Brady","LeBron James","Floyd Mayweather Jr","Joe Montana","Alex Morgan","Jesse Owens","Derek Jeter","Mark Spitz","Simone Biles","Michael Phelps")
            else -> listOf("")
        }
        return nombres
    }
    fun listaDeportes(): List<String> {
        var deportes = when (paises){
            "Argentina" -> listOf(
                "Maratón -- Si esta activo", "Maratón -- Si esta activo", "400 metros -- Si esta activo", "3.000 metros con obstáculos -- Si esta activo", "Lanzamiento de bala -- Si esta activo", "Lanzamiento de martillo -- No esta activo", "Fútbol -- Si esta activo", "Judo -- No esta activo", "Vela -- No esta activo", "Canotaje -- Si esta activo")
            "España" -> listOf(
                "Futbol -- No esta activo","Taekwondo -- Si esta activo","Piragüismo en eslalon -- Si esta activo","Artístico -- Si esta activo","Futbol -- Si esta activo","Karate -- Si esta activo","Piragüismo en esprint -- Si esta activo","Piragüismo en esprint -- Si esta activo","Piragüismo en esprint -- Si esta activo","Piragüismo en esprint -- Si esta activo")
            "Estados Unidos" -> listOf(
                "Fútbol americano -- No esta activo","Baloncesto -- Si esta activo","Boxeo -- No esta activo","Fútbol americano -- No esta activo","Fútbol femenino -- No esta activo","Atletismo -- No esta activo","Béisbol -- No esta activo","Natación -- No esta activo","Gimnasia artística -- Si esta activo","Natación -- No esta activo")
            else -> listOf("")
        }
        return deportes
    }
}