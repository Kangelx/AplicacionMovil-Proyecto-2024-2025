package com.example.equipo3.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("personalID")
    public val personalID: Int,
    @SerializedName("nombre")
    public val nombre: String,
    @SerializedName("apellidos")
    public val apellidos: String
)