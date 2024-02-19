package com.example.equipo3.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Incidencia (
    @SerializedName("num")
    val num: Int,
    @SerializedName("tipo")
    val tipo: String,
    @SerializedName("descripcion")
    val descripcion: String
) : Serializable

