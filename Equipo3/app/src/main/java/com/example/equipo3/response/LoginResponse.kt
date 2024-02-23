package com.example.equipo3.response

import com.example.equipo3.model.Incidencia
import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("personalID")
    public val personalID: Int,
    @SerializedName("nombre")
    public val nombre: String,
    @SerializedName("apellidos")
    public val apellidos: String,
    @SerializedName("ListaIncidencias")
    public val listaIncidencias: List<Incidencia>
)