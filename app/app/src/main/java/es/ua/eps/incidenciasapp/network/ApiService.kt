package es.ua.eps.incidenciasapp.network

import es.ua.eps.incidenciasapp.dataclass.DevolverTodoDTO
import es.ua.eps.incidenciasapp.dataclass.IncidenciaDTO
import es.ua.eps.incidenciasapp.dataclass.LoginData

import layout.Incidencia
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/incidencia")
    fun getIncidencias(): Call<List<Incidencia>>

    @GET("/incidencia/{num}")
    fun buscarPorNum(@Path("num") num: String,
                     @Header("Authorization") token: String): Call<Incidencia>



    @DELETE("incidencia/{num}")
    fun borrarIncidencia(@Path("num") num: Int, @Header("Authorization") token: String): Call<Void>

    @POST("/incidencia")
    fun crearIncidencia(@Body incidencia: Incidencia,@Header("Authorization") token: String): Call<Void>

    @POST("/login")
    fun login(@Body loginData: LoginData): Call<DevolverTodoDTO>

    @GET("/incidencia")
    fun obtenerIncidencias(@Header("Authorization") token: String): Call<List<Incidencia>>



}