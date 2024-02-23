package com.example.equipo3.model

import android.security.identity.PersonalizationData
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.PasswordAuthentication


data class ProfesorDataResponse(
    @SerializedName("educantabria") val nombre: String,
    @SerializedName("perfil") val apellidos: String

//    @SerializedName("incidencias")val incidencias: List<IncidenciaItemResponse>
)

data class IncidenciaItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)
//data class Usuario (
////    @SerializedName("personalID")
////    public val personalID: Int,
//    @SerializedName("educantabria")
//    public val educantabria: String,
//    @SerializedName("password")
//    public val password: String,
////    @SerializedName("perfil")
////    public val perfil: String
//)