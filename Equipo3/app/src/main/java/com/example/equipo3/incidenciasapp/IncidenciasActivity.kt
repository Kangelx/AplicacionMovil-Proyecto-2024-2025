package com.example.equipo3.incidenciasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo3.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/*
abierto,
asignado,
en proceso,
enviado a INFORTEC,
resuelto,
cerrado.
 */
class IncidenciasActivity : AppCompatActivity() {

    private val estados = listOf(
        EstadosIncidencias.Abierto,
        EstadosIncidencias.Asignado,
        EstadosIncidencias.EnProceso,
        EstadosIncidencias.EnviadoInfortec,
        EstadosIncidencias.Resuelto,
        EstadosIncidencias.Cerrado
    )

    private val incidencias = mutableListOf(
        Estados("Abierto", EstadosIncidencias.Abierto),
        Estados("Asignado", EstadosIncidencias.Asignado),
        Estados("En proceso", EstadosIncidencias.EnProceso),
        Estados("Enviado a INFORTEC", EstadosIncidencias.EnviadoInfortec),
        Estados("Resuelto", EstadosIncidencias.Resuelto),
        Estados("Cerrado", EstadosIncidencias.Cerrado)
    )

    private lateinit var rvEstados: RecyclerView
    private lateinit var estadosAdapter: EstadosAdapter

    private lateinit var rvIncidencias: RecyclerView
    private lateinit var incidenciasAdapter: IncidenciasAdapter

    private lateinit var masIncidencias: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incidencias)

        initComponent()
        initUI()
        initListener()
    }

    private fun initComponent() {
        rvIncidencias = findViewById(R.id.rvIncidencias)
        rvEstados = findViewById(R.id.rvEstados)
        masIncidencias = findViewById(R.id.fabMasIncidencias)
    }

    private fun initUI() {
        TODO("Not yet implemented")
    }

    private fun initListener() {
        masIncidencias.setOnClickListener { showDialog() }
    }

    private fun showDialog(){
        
    }
}