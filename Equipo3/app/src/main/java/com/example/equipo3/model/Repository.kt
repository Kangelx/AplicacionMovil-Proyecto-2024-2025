package com.example.equipo3.model

import com.example.equipo3.ApiService.AlpacApi

class Repository {
    suspend fun getIncidencias(): List<Incidencia> = AlpacApi.retrofitService.getIncidencias()
    suspend fun getUsuario(user: String, password: String) = AlpacApi.retrofitService.getUsuario(user, password)
    suspend fun crearIncidencia(incidencia: Incidencia) = AlpacApi.retrofitService.crearIncidencia(incidencia)
    suspend fun modificarIncidencia(id: Int, incidencia: Incidencia) = AlpacApi.retrofitService.modificarIncidencia(id, incidencia)
}