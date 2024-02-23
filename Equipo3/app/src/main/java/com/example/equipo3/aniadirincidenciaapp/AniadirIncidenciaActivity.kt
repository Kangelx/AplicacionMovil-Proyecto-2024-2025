package com.example.equipo3.aniadirincidenciaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.equipo3.ApiService.ApiService
import com.example.equipo3.databinding.ActivityAniadirIncidenciaBinding
import com.example.equipo3.incidenciasapp.Estado
import com.example.equipo3.model.Incidencia
import com.example.equipo3.model.Subtipo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AniadirIncidenciaActivity : AppCompatActivity() {

        private lateinit var retrofit: Retrofit
        private lateinit var binding: ActivityAniadirIncidenciaBinding
        private lateinit var apiService: ApiService

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityAniadirIncidenciaBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnAceptar.setOnClickListener {
                enviarIncidenciaAlServidor()
            }

            binding.btnCancelar.setOnClickListener {
            }


            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            binding.edFecha.setText(currentDate)
            binding.edFecha.isFocusable=false

            val estadosArray = Estado.values().map { it.name.toLowerCase() }.toTypedArray()
            val estadosAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estadosArray)
            estadosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerEstados.adapter = estadosAdapter

            // Configuración del Spinner de Prioridad
            val prioridadArray = arrayOf("baja", "media", "alta", "critica")
            val prioridadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prioridadArray)
            prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerPrioridad.adapter = prioridadAdapter


            binding.btnAceptar.setOnClickListener {
                enviarIncidenciaAlServidor()
            }
        }

        private fun enviarIncidenciaAlServidor() {
            val tipo = binding.edTipo.text.toString()
            val descripcion = binding.edDescripcion.text.toString()

            val fecha = binding.edFecha.text.toString()
            val subtipo = binding.edSubTipo.text.toString()
            val creadorId = binding.edCreadorId.text.toString()
            val prioridad = obtenerPrioridadDesdeSpinner(binding.spinnerPrioridad.selectedItemPosition)
            val estado = obtenerEstadoDesdeSpinner(binding.spinnerEstados.selectedItemPosition)

            val incidencia = Incidencia(
                tipo = tipo,
                subtipoId = Subtipo(0, "EQUIPOS", subtipo, null),
                fechaCreacion = fecha,
                fechaCierre = null,
                descripcion = descripcion,
                estado = estado,
                adjuntoUrl = null,
                equipoId = null,
                prioridad = prioridad
            )

            // Aquí debes tener tu instancia de ApiService y Retrofit
            // Reemplaza ApiServiceDummy con el nombre real de tu interfaz de servicio
            // Reemplaza retrofitDummy con tu instancia real de Retrofit
            retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.56.1:4001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)

            val call = apiService.crearIncidencia(incidencia)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d("ApiSuccess", "Incidencia enviada exitosamente")
                        // Puedes realizar alguna acción adicional después de enviar la incidencia
                    } else {
                        Log.e("ApiError", "Error al enviar la incidencia: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("ApiError", "Error al enviar la incidencia", t)
                    t.printStackTrace()

                    // Imprimir el cuerpo de la respuesta
                    if (t is HttpException) {
                        val errorBody = t.response()?.errorBody()?.string()
                        Log.e("ApiError", "Error Body: $errorBody")
                    }
                }
            })
        }

        private fun obtenerPrioridadDesdeSpinner(position: Int): String {
            return when (position) {
                0 -> "baja"
                1 -> "media"
                2 -> "alta"
                3 -> "critica"
                else -> "baja"
            }
        }

        private fun obtenerEstadoDesdeSpinner(position: Int): String {
            return when (position) {
                0 -> Estado.ABIERTA.name.toLowerCase()
                1 -> Estado.ASIGNADA.name.toLowerCase()
                2 -> Estado.EN_PROCESO.name.toLowerCase()
                3 -> Estado.ENVIADO_A_INFORTEC.name.toLowerCase()
                4 -> Estado.RESUELTA.name.toLowerCase()
                5 -> Estado.CERRADA.name.toLowerCase()
                else -> Estado.ABIERTA.name.toLowerCase()
            }
        }
    }
