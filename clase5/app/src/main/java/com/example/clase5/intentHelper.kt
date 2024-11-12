package com.example.clase5

import android.content.Context
import android.content.Intent

class intentHelper {
    fun intentDeportista(nombre:String, deporte:String, context: Context) {
        val intent = Intent(context, Detalle::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("deporte", deporte)
        context.startActivity(intent)
    }
}