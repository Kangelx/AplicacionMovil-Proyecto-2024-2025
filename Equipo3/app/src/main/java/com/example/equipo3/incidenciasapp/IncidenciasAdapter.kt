package com.example.equipo3.incidenciasapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo3.R

class IncidenciasAdapter (var incidencias: List<Estados>, private val estadosElegidos: (Int) -> Unit):
 RecyclerView.Adapter<IncidenciasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidenciasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incidencia, parent, false)
        return IncidenciasViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidenciasViewHolder, position: Int) {
        holder.render(incidencias[position])
        holder.itemView.setOnClickListener { estadosElegidos(position) }
    }

    override fun getItemCount() = incidencias.size
}