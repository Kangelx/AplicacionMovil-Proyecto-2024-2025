package es.ua.eps.incidenciasapp.view


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.incidenciasapp.R
import es.ua.eps.incidenciasapp.databinding.ActivityVerIncidenciasBinding
import es.ua.eps.incidenciasapp.login.ApiClient
import es.ua.eps.incidenciasapp.network.ApiService
import es.ua.eps.incidenciasapp.settings.SettingsActivity
import layout.Incidencia
import layout.IncidenciaAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VerIncidenciasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerIncidenciasBinding
    private var incidencias: List<Incidencia> = ArrayList()
    private lateinit var adapter: IncidenciaAdapter
    private lateinit var apiService: ApiService
    private lateinit var originalIncidencias: List<Incidencia>
    private lateinit var retrofit: Retrofit
    private lateinit var categoryAdapter: CategoryAdapter
    private val apiClient = ApiClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerIncidenciasBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.fabAddTaskAccount.setOnClickListener {
            // Iniciar la actividad CuentaActivity
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.fabAddTaskAniadir.setOnClickListener {
            startActivity(Intent(this, AniadirIncidenciaActivity::class.java))
        }




        binding.rvIncidencias.layoutManager = LinearLayoutManager(this)
        adapter = IncidenciaAdapter(
            { incidencia -> mostrarDetallesIncidencia(incidencia) },
            { incidencia -> borrarIncidencia(incidencia) },
            { incidencia -> abrirActividadComentarios(incidencia) }
        )
        binding.rvIncidencias.adapter = adapter








        val rvCategories: RecyclerView = findViewById(R.id.rvCategories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

// Definir las categorías con sus colores correspondientes
        val categories = listOf(
            TaskCategory.Abierto(),
            TaskCategory.Asignado(),
            TaskCategory.EnProceso(),
            TaskCategory.EnviadoAInfortec(),
            TaskCategory.Resuelto(),
            TaskCategory.Cerrado()
        )

        val categoryAdapter = CategoryAdapter(this, categories) { selectedCategory ->
            filterIncidenciasByCategory(selectedCategory)
        }

        categoryAdapter.setOnCategoryDeselectedListener {
            filterIncidenciasByCategory()
        }

        rvCategories.adapter = categoryAdapter


        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.56.1:4001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

       apiService = retrofit.create(ApiService::class.java)
        val authToken = obtenerTokenDeAutenticacion()
        Log.d("VerIncidenciasActivity", "Token de autenticación: $authToken")
        val call = apiService.obtenerIncidencias("Bearer $authToken")
        call.enqueue(object : Callback<List<Incidencia>> {
            override fun onResponse(call: Call<List<Incidencia>>, response: Response<List<Incidencia>>) {
                if (response.isSuccessful) {
                    Log.d("ApiSuccess", "Respuesta exitosa")

                    val incidencias = response.body()
                    incidencias?.let {
                        originalIncidencias = it  // Inicializa originalIncidencias con la lista original
                        adapter.setIncidencias(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Incidencia>>, t: Throwable) {
                Log.e("ApiError", "Error en la llamada a la API",t)
                t.printStackTrace()
            }
        })


        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    buscarIncidenciasPorNum(newText)


                } else {
                    // Restaura la lista original si el texto de búsqueda está vacío
                    adapter.setIncidencias(originalIncidencias)
                }
                return false
            }
        })
    }



    private fun mostrarDetallesIncidencia(incidencia: Incidencia) {
        val intent = Intent(this, EditIncidenciasActivity::class.java)
        intent.putExtra("num", incidencia.num)
        intent.putExtra("tipo", incidencia.tipo)
        intent.putExtra("subtipo",incidencia.subtipoId.tipo)
        intent.putExtra("subsubtipo",incidencia.subtipoId.subtipoNombre)
        val aula = incidencia.equipoId?.aula_num?.codigo ?: "Aula no especificada"


        intent.putExtra("aula",aula)



        intent.putExtra("fechaCreacion",incidencia.fechaCreacion)
        intent.putExtra("descripcion",incidencia.descripcion)
        intent.putExtra("estado",incidencia.estado)
        // Agrega más extras según sea necesario
        startActivity(intent)
    }

    private fun buscarIncidenciasPorNum(num: String) {
        val authToken = obtenerTokenDeAutenticacion()
        val call = apiService.buscarPorNum(num,"Bearer $authToken")

        call.enqueue(object : Callback<Incidencia> {
            override fun onResponse(call: Call<Incidencia>, response: Response<Incidencia>) {
                runOnUiThread {
                    if (response.isSuccessful) {

                        val incidencia = response.body()
                        incidencia?.let {
                            adapter.setIncidencias(listOf(it))
                        } ?: run {
                            // Manejar la situación en la que no se encontraron resultados
                            adapter.setIncidencias(emptyList())
                        }
                    } else {
                        // Manejar la respuesta no exitosa si es necesario
                        Log.e("ApiError", "Error en la llamada a la API: ${response.code()}")
                        adapter.setIncidencias(emptyList())
                    }
                }
            }

            override fun onFailure(call: Call<Incidencia>, t: Throwable) {
                runOnUiThread {

                    Log.e("ApiError", "Error en la llamada a la API", t)
                    adapter.setIncidencias(emptyList())
                }
            }
        })
    }



    private fun incidenciaMatchesCategory(incidencia: Incidencia, selectedCategory: TaskCategory): Boolean {
        // Puedes ajustar esto según tu modelo de datos y cómo se almacenan las categorías en las incidencias
        when (selectedCategory) {
            is TaskCategory.Abierto -> return incidencia.estado == "abierta"
            is TaskCategory.Asignado -> return incidencia.estado == "asignada"
            is TaskCategory.Resuelto -> return incidencia.estado == "resuelta"
            is TaskCategory.Cerrado -> return incidencia.estado == "cerrada"
            is TaskCategory.EnviadoAInfortec -> return incidencia.estado == "enviada_a_Infortec"
            is TaskCategory.EnProceso -> return incidencia.estado == "en_proceso"
            // Añadir lógica para otras categorías según sea necesario
            else -> return false
        }
    }

    private fun filterIncidenciasByCategory(selectedCategory: TaskCategory? = null) {
        if (selectedCategory == null) {
            Log.d("FilterDebug", "No hay categoría seleccionada. Mostrando lista completa.")
            adapter.setIncidencias(originalIncidencias)
        } else {
            Log.d("FilterDebug", "Filtrando por categoría: $selectedCategory")
            // Filtrar las incidencias según la categoría seleccionada
            val filteredIncidencias = originalIncidencias.filter { incidencia ->
                incidenciaMatchesCategory(incidencia, selectedCategory)
            }

            // Actualizar el RecyclerView con las incidencias filtradas
            adapter.setIncidencias(filteredIncidencias)
        }
    }




    private fun obtenerTokenDeAutenticacion(): String? {
        val sharedPreferences = getSharedPreferences("mi_app_preferencias", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }

    private fun borrarIncidencia(incidencia: Incidencia) {
        val authToken = obtenerTokenDeAutenticacion()
        val numIncidencia = incidencia.num ?: 0 // o cualquier valor predeterminado que desees

        // Crear un diálogo de confirmación
        AlertDialog.Builder(this)
            .setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro de que deseas eliminar esta incidencia?")
            .setPositiveButton("Sí") { dialog, which ->
                // Realizar la llamada a la API para borrar la incidencia
                val call = apiService.borrarIncidencia(numIncidencia, "Bearer $authToken")

                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            // Borrado exitoso, actualiza la lista de incidencias
                            val updatedIncidencias = originalIncidencias.toMutableList()
                            updatedIncidencias.remove(incidencia)

                            // Actualiza el RecyclerView con las incidencias actualizadas
                            adapter.setIncidencias(updatedIncidencias)
                            adapter.notifyDataSetChanged()
                            Log.d("DeleteIncidencia", "Incidencia eliminada con éxito")
                        } else {
                            // Manejar el caso en el que el borrado no fue exitoso
                            Log.e("ApiError", "Error en la llamada a la API: ${response.code()}")
                            Log.e("ApiError", "Error body: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        // Manejar el caso en que la llamada a la API falla
                        Log.e("ApiError", "Error en la llamada a la API", t)
                    }
                })
            }
            .setNegativeButton("No", null)
            .show()
    }



    private fun abrirActividadComentarios(incidencia: Incidencia) {
        // Lógica para abrir la actividad de comentarios
        val intent = Intent(this, ComentariosActivity::class.java)
        intent.putExtra("numIncidencia", incidencia.num)
        startActivity(intent)
    }



}




