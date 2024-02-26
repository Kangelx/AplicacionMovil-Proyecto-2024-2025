package com.example.equipo3.incidenciasapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo3.R
import com.example.equipo3.databinding.ItemIncidenciaBinding
import com.example.equipo3.model.Incidencia

class IncidenciaAdapter (private val clickListener: (Incidencia) -> Unit): RecyclerView.Adapter<IncidenciaAdapter.ViewHolder>() {

    private var incidencias: List<Incidencia> = ArrayList()

    fun setIncidencias(incidencias: List<Incidencia>) {
        this.incidencias = incidencias
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIncidenciaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val incidencia = incidencias[position]
        holder.bind(incidencia)
        holder.itemView.setOnClickListener { clickListener(incidencia) }
    }

    override fun getItemCount(): Int {
        return incidencias.size
    }

    class ViewHolder(private val binding: ItemIncidenciaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(incidencia: Incidencia) {
            binding.numTextView.text = incidencia.num.toString()
            binding.tipoTextView.text = incidencia.tipo
            binding.descripcionTextView.text = incidencia.descripcion
            val colorResId = when (incidencia.estado) {
                "abierta" -> R.color.estado_abierto
                "asignada" -> R.color.estado_asignado
                "resuelta" -> R.color.estado_resuelto
                "en_proceso" -> R.color.estado_enProceso
                "cerrada"-> R.color.estado_cerrado
                "enviada_a_Infortec" -> R.color.estado_enviadoInfortec

                // Agrega más casos según sea necesario
                else -> R.color.colorGrisClaro// Color predeterminado si el estado no coincide
            }
            binding.colorIndicator.setBackgroundColor(ContextCompat.getColor(binding.root.context, colorResId))
        }
    }
}