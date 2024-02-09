package com.example.equipo3.incidenciasapp

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo3.R

class EstadosViewHolder (view: View) : RecyclerView.ViewHolder(view){
    private val tvIncidencia: TextView = view.findViewById(R.id.tvIncidencia)


    fun render(incidencia: EstadosIncidencias){
        if (incidencia.isSelected){
            tvIncidencia.paintFlags = tvIncidencia.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvIncidencia.paintFlags = tvIncidencia.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        tvIncidencia.text = incidencia.toString()


    }
}