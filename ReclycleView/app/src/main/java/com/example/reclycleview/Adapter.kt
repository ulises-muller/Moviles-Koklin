package com.example.reclycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class Adapter(): ListAdapter<Equipo, Adapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val logo: ImageView = view.findViewById(R.id.logo)
        private val nombre: TextView = view.findViewById(R.id.nombre)
        private val stadium: TextView = view.findViewById(R.id.equipo)
        private val pais: ImageView = view.findViewById(R.id.pais)

        fun bind(equipo: Equipo) {
            nombre.text = equipo.nombre
            stadium.text = equipo.stadium

            val imagenPais = when (equipo.pais) {
                Pais.ARGENTINA -> R.drawable.arg
                Pais.BRASIL -> R.drawable.bra
                Pais.COLOMBIA -> R.drawable.col
                Pais.CHILE -> TODO()
                Pais.ECUADOR -> TODO()
            }
            logo.setImageResource(imagenPais)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipo = getItem(position)
        holder.bind(equipo)
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Equipo>() {
        override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem == newItem
        }
    }
}
