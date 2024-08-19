package com.example.moviles2

data class Valores (
    var valor1: Int,
    val valor2: Int
){
    fun calcularSuma() : Int{
        return valor1 + valor2
    }
}

class ValoresClass(){
    var numero1 : Int = 0
    var numero2 : Int = 1

    fun calcularSuma(): Int {
        return numero2 + numero1
    }
}
