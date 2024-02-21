package com.example.equipo3.model

import android.security.identity.PersonalizationData
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.PasswordAuthentication

data class Usuario (
//    @SerializedName("personalID")
//    public val personalID: Int,
    @SerializedName("educantabria")
    public val educantabria: String,
    @SerializedName("password")
    public val password: String,
//    @SerializedName("perfil")
//    public val perfil: String
)