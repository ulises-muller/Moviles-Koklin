package com.example.clase5

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinnerPais)
        listView = findViewById(R.id.listEquipos)

        val pais = listOf("Uruguay","Argentina","Colombia")

        val equipoArg = listOf("River Plate","Boca Juniors","Racing Club")
        val equipoUru = listOf("Montevideo Wanderers","Peñarol","Nacional")
        val equipoCol = listOf("América de Cali","Millonarios","Atlético Nacional")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pais)
        val adapterArg = ArrayAdapter(this, android.R.layout.simple_list_item_1, equipoArg)
        val adapterUru = ArrayAdapter(this, android.R.layout.simple_list_item_1, equipoUru)
        val adapterCol = ArrayAdapter(this, android.R.layout.simple_list_item_1, equipoCol)

        spinner.adapter = adapter



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val paises = spinner.selectedItem.toString()
                if (paises == "Argentina") {
                    listView.adapter = adapterArg
                } else if (paises == "Uruguay") {
                    listView.adapter = adapterUru
                } else if (paises == "Colombia") {
                    listView.adapter = adapterCol
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
}