package com.example.equipo3.response

import com.google.gson.annotations.SerializedName

data class ProfesorDataResponse (
    @SerializedName("personalID") public val personalID: Int,
    @SerializedName("nombre") public val nombre: String,
    @SerializedName("token") public val token: AuthTokenResponse?,
    @SerializedName("incidencias") val listaIncidencias: List<IncidenciaItemResponse>
)

data class AuthTokenResponse(
    @SerializedName("token") public val token: String
)
data class IncidenciaItemResponse(
    @SerializedName("num") val num: Int,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("subtipoId") val subtipoId: SubtipoItemResponse,
    @SerializedName("fechaCreacion") val fechaCreacion: String,
    @SerializedName("fechaCierre") val fechaCierre: String?,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("adjuntoUrl") val adjuntoUrl: String?,
    @SerializedName("equipoId") val equipoId: EquipoItemResponse?,
    @SerializedName("prioridad") val prioridad: String
)
data class SubtipoItemResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("subtipoNombre") val subtipoNombre: String,
    @SerializedName("subSubtipo") val subSubtipo: Any?
)
data class EquipoItemResponse(
    @SerializedName("aula_num")val aula_num: AulaItemResponse
)
data class AulaItemResponse(
    @SerializedName("num")val num: Int,
    @SerializedName("codigo")val codigo: String,
    @SerializedName("descripcion")val descripcion: String,
    @SerializedName("planta")val planta: Int
)
