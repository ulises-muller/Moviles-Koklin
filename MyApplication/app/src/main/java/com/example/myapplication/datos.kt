package com.example.myapplication

data class Datos(
    var nombre:String,
    var apellido:String,
    var edad: String
){
    fun imprimirUsuario(){
        println("Nombre: $nombre")
        println("Apellido: $apellido")
        println("Edad: $edad")
    }
}
