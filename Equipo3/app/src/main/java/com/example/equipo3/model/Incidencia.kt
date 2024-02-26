package com.example.equipo3.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Incidencia (
    @SerializedName("num") val num: Int?,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("subtipoId") val subtipoId: Subtipo,
    @SerializedName("fechaCreacion") val fechaCreacion: String,
    @SerializedName("fechaCierre") val fechaCierre: String?,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("adjuntoUrl") val adjuntoUrl: String?,
    @SerializedName("equipoId") val equipoId: Equipo?,
    @SerializedName("prioridad") val prioridad: String
)
data class Subtipo(
    @SerializedName("id") val id: Int,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("subtipoNombre") val subtipoNombre: String,
    @SerializedName("subSubtipo") val subSubtipo: Any?
)
data class Equipo(
    @SerializedName("aula_num")val aula_num: Aula
)
data class Aula(
    @SerializedName("num")val num: Int,
    @SerializedName("codigo")val codigo: String,
    @SerializedName("descripcion")val descripcion: String,
    @SerializedName("planta")val planta: Int
)



