package com.example.equipo3.ApiService;


import com.example.equipo3.model.Incidencia
import com.example.equipo3.model.ProfesorDataResponse

import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("http://10.0.13.119:4001/perfil/")
    suspend fun getProfesor(
        @Body usuario : ProfesorDataResponse
    ): Response<ProfesorDataResponse>

    @PUT("/incidencia")
    suspend fun modificarIncidencia(
        @Path(value = "id")id: Int,
        @Body()incidencia: Incidencia
    ): Incidencia?
    /*
    @POST("/incidencia")
    suspend fun crearIncidencia(
        @Body()incidencia: Incidencia
    ): Incidencia?
     */

    //@POST(value = "login")
//    @GET("/perfil/")
//    fun postLogin(@Query(value = "educantabria") email: String, @Query (value = "password") password: String):
//            Call<LoginResponse>
//
//    companion object Factory{
//        private const val URL_BASE = "http://10.0.13.119:4001/"
//         fun create(): ApiService {
//             val retrofit = Retrofit.Builder()
//                 .baseUrl(URL_BASE)
//                 .addConverterFactory(GsonConverterFactory.create())
//                 .build()
//             return retrofit.create(ApiService::class.java)
//         }
//    }
    /*
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
    */

}
