package com.example.clase4

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class intentHelper {
    fun getIntent(producto:String, pm:String, context: Context) {
        val intent = Intent(context, MainActivity2::class.java)
        intent.putExtra("product", producto)
        intent.putExtra("price", pm)
        context.startActivity(intent)
    }
}