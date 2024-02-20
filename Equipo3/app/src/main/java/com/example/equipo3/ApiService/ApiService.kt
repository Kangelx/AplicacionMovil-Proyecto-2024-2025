package com.example.equipo3.ApiService;

import com.example.equipo3.model.Incidencia
import com.example.equipo3.model.Usuario

import retrofit2.Call;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val URL_BASE = "http://10.0.13.119:4001/"
private val retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
interface ApiService {
    @GET("/incidencia")
    suspend fun getIncidencias(): List<Incidencia>

    @GET("/perfil")
    suspend fun getUsuario(
        @Header(value = "educantabria")educantabria: String//,
        //@Header(value = "password")password: String
    ): Usuario?

    @POST("/incidencia")
    suspend fun crearIncidencia(
        @Body()incidencia: Incidencia
    ): Incidencia?

    @PUT("/incidencia")
    suspend fun modificarIncidencia(
        @Path(value = "id")id: Int,
        @Body()incidencia: Incidencia
    ): Incidencia?
}

object AlpacApi{
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
