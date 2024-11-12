package com.example.reclycleview

data class Equipo (
    val id: Int,
    val logo: String,
    val pais: Pais,
    val nombre: String,
    var stadium: String
)

enum class Pais{
    ARGENTINA,
    BRASIL,
    CHILE,
    COLOMBIA,
    ECUADOR,
}
