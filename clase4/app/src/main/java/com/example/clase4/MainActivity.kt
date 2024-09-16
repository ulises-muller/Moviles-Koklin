package com.example.clase4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

//! listOf() inmutable
//! mutableListOf() mutable

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerPM: Spinner
    private lateinit var listProd: ListView
    private lateinit var selectedPaymentMethod: String
    private lateinit var selectedProduct: String
    private val intenthelper = intentHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerPM = findViewById(R.id.spinnerPM)
        listProd = findViewById(R.id.listProd)

        val paymentmethods = listOf("MasterCard", "Visa", "MODO", "Visa Debito", "Mercado pago")

        val products = mutableListOf("jabon","detergente","pasta de dientes", "lavandina","clorox","cepillo de dientes","shampoo","crema de afeitar","crema corporal","crema solar")

        val adapterProd = ArrayAdapter(this, android.R.layout.simple_list_item_1, products)
        val adapterPm = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, paymentmethods)
        spinnerPM.adapter = adapterPm
        listProd.adapter = adapterProd

        listProd.setOnItemClickListener { parent, view, position, id ->
            selectedProduct = products[position]
            selectedPaymentMethod = spinnerPM.selectedItem.toString()

            intenthelper.getIntent(selectedProduct, selectedPaymentMethod, this)

            /* val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("product", selectedProduct)
            intent.putExtra("price", selectedPaymentMethod)
            startActivity(intent)*/
        }
    }
}