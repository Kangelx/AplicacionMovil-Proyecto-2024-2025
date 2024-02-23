package com.example.equipo3.response

import com.example.equipo3.model.Incidencia
import com.google.gson.annotations.SerializedName

data class ProfesorDataResponse (
    @SerializedName("personalID") public val personalID: Int,
    @SerializedName("nombre") public val nombre: String,
    @SerializedName("apellidos") public val apellidos: String,
    @SerializedName("incidencias") val listaIncidencias: List<IncidenciaItemResponse>
)

data class IncidenciaItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)
