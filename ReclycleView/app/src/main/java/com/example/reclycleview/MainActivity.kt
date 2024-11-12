package com.example.reclycleview

import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var teamsAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rV)
        recyclerView.layoutManager = LinearLayoutManager(this)


        teamsAdapter = Adapter(getTeams())
        recyclerView.adapter = teamsAdapter


    }
    fun getTeams(): List<Equipo> {
        return listOf(
            Equipo(1,"equipo 1",Pais.BRASIL,"Tigre","Jose della Giovonni"),
            Equipo(2,"equipo 2",Pais.ARGENTINA,"river","Giovonni"),
            Equipo(3,"equipo 3",Pais.COLOMBIA,"boca","della"),
        )
    }
}