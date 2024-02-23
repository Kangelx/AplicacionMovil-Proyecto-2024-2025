package com.example.equipo3.ApiService;


import com.example.equipo3.model.Incidencia
import com.example.equipo3.model.UsuarioData
import com.example.equipo3.response.ProfesorDataResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("http://10.0.13.119:4001/perfil/")
    suspend fun getProfesor(
        @Body usuario : UsuarioData
    ): Response<ProfesorDataResponse>

    @PUT("/incidencia")
    suspend fun modificarIncidencia(
        @Path(value = "id")id: Int,
        @Body()incidencia: Incidencia
    ): Incidencia?

    @POST("/incidencia")
    suspend fun crearIncidencia(
        @Path(value = "id")id: Int,
        @Body()incidencia: Incidencia
    ): Incidencia?
}
