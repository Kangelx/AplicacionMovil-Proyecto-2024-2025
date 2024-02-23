package com.example.equipo3.model

import android.security.identity.PersonalizationData
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.PasswordAuthentication


data class UsuarioData(
    @SerializedName("educantabria") val nombre: String,
    @SerializedName("perfil") val apellidos: String
)