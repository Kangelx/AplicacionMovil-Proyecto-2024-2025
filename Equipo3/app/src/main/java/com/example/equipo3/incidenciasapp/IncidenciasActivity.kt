package com.example.equipo3.incidenciasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo3.R
import com.example.equipo3.databinding.ActivityIncidenciasBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

    private lateinit var binding: ActivityIncidenciasBinding

    private lateinit var rvEstados: RecyclerView
    private lateinit var estadosAdapter: EstadosAdapter

    private lateinit var rvIncidencias: RecyclerView
    private lateinit var incidenciasAdapter: IncidenciasAdapter

    private lateinit var masIncidencias: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIncidenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponent()
        initUI()
        initListener()
    }

    private fun initListener() {
        masIncidencias.setOnClickListener { showDialog() }
    }

    private fun showDialog(){

    }

    private fun initComponent() {
        rvIncidencias = findViewById(R.id.rvIncidencias)
        rvEstados = findViewById(R.id.rvEstados)
        masIncidencias = findViewById(R.id.fabMasIncidencias)
    }

    private fun initUI() {
        estadosAdapter = EstadosAdapter(estados) { position -> updateEstados(position)}
        rvEstados.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvIncidencias.adapter = estadosAdapter

        incidenciasAdapter = IncidenciasAdapter(incidencias) {position ->onItemSelected(position)}
        rvIncidencias.layoutManager = LinearLayoutManager(this)
        rvIncidencias.adapter = incidenciasAdapter
    }

    private fun onItemSelected(position: Int){
        incidencias[position].isSelected = !incidencias[position].isSelected
        updateIncidencias()
    }

    private fun updateEstados(position: Int){
        estados[position].isSelected = !estados[position].isSelected
        estadosAdapter.notifyItemChanged(position)
        updateIncidencias()
    }

    private fun updateIncidencias(){
        val selectedEstados: List<EstadosIncidencias> = estados.filter { it.isSelected }
        val nuevaIncidencia = incidencias.filter {selectedEstados.contains(it.estado)}
        incidenciasAdapter.incidencias = nuevaIncidencia
        incidenciasAdapter.notifyDataSetChanged()
    }
}