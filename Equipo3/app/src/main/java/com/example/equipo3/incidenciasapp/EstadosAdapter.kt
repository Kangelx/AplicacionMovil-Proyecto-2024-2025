package com.example.equipo3.incidenciasapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo3.R

class EstadosAdapter (var estados: List<Estados>, private val incidenciaElegida: (Int) -> Unit):
RecyclerView.Adapter<EstadosViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estado, parent, false)
        return EstadosViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstadosViewHolder, position: Int) {
        holder.render(estados[position])
        holder.itemView.setOnClickListener{ incidenciaElegida(position)}
    }

    override fun getItemCount() = estados.size
}