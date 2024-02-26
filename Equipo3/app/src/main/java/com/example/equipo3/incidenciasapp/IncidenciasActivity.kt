package com.example.equipo3.incidenciasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo3.ApiService.ApiService
import com.example.equipo3.R
import com.example.equipo3.aniadirincidenciaapp.AniadirIncidenciaActivity
import com.example.equipo3.databinding.ActivityIncidenciasBinding
import com.example.equipo3.model.Incidencia
import com.example.equipo3.settings.SettingsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IncidenciasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIncidenciasBinding
    private var incidencias: List<Incidencia> = ArrayList()
    private lateinit var adapter: IncidenciaAdapter
    private lateinit var apiService: ApiService
    private lateinit var originalIncidencias: List<Incidencia>
    private lateinit var retrofit: Retrofit
    private lateinit var categoryAdapter: EstadosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncidenciasBinding.inflate(layoutInflater)

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
        adapter =  IncidenciasAdapter { incidencia -> mostrarDetallesIncidencia(incidencia) }
        binding.rvIncidencias.adapter = adapter








        val rvCategories: RecyclerView = findViewById(R.id.rvCategories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

// Definir las categorías con sus colores correspondientes
        val categories = listOf(
            EstadosIncidencias.Abierto(),
            EstadosIncidencias.Asignado(),
            EstadosIncidencias.EnProceso(),
            EstadosIncidencias.EnviadoAInfortec(),
            EstadosIncidencias.Resuelto(),
            EstadosIncidencias.Cerrado()
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

        val call = apiService.getIncidencias()
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
        val call = apiService.buscarPorNum(num)

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

//    private val estados = listOf(
//        EstadosIncidencias.Abierto,
//        EstadosIncidencias.Asignado,
//        EstadosIncidencias.EnProceso,
//        EstadosIncidencias.EnviadoInfortec,
//        EstadosIncidencias.Resuelto,
//        EstadosIncidencias.Cerrado
//    )
//
//    private val incidencias = mutableListOf(
//        Estados("Abierto", EstadosIncidencias.Abierto),
//        Estados("Asignado", EstadosIncidencias.Asignado),
//        Estados("En proceso", EstadosIncidencias.EnProceso),
//        Estados("Enviado a INFORTEC", EstadosIncidencias.EnviadoInfortec),
//        Estados("Resuelto", EstadosIncidencias.Resuelto),
//        Estados("Cerrado", EstadosIncidencias.Cerrado)
//    )
//
//    private lateinit var binding: ActivityIncidenciasBinding
//
//    private lateinit var rvEstados: RecyclerView
//    private lateinit var estadosAdapter: EstadosAdapter
//
//    private lateinit var rvIncidencias: RecyclerView
//    private lateinit var incidenciasAdapter: IncidenciasAdapter
//
//    private lateinit var masIncidencias: FloatingActionButton
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityIncidenciasBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        initComponent()
//        initUI()
//        initListener()
//    }
//
//    private fun initListener() {
//        masIncidencias.setOnClickListener { showDialog() }
//    }
//
//    private fun showDialog(){
//
//    }
//
//    private fun initComponent() {
//        rvIncidencias = findViewById(R.id.rvIncidencias)
//        rvEstados = findViewById(R.id.rvEstados)
//        masIncidencias = findViewById(R.id.fabMasIncidencias)
//    }
//
//    private fun initUI() {
//        estadosAdapter = EstadosAdapter(estados) { position -> updateEstados(position)}
//        rvEstados.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rvIncidencias.adapter = estadosAdapter
//
//        incidenciasAdapter = IncidenciasAdapter(incidencias) {position ->onItemSelected(position)}
//        rvIncidencias.layoutManager = LinearLayoutManager(this)
//        rvIncidencias.adapter = incidenciasAdapter
//    }
//
//    private fun onItemSelected(position: Int){
//        incidencias[position].isSelected = !incidencias[position].isSelected
//        updateIncidencias()
//    }
//
//    private fun updateEstados(position: Int){
//        estados[position].isSelected = !estados[position].isSelected
//        estadosAdapter.notifyItemChanged(position)
//        updateIncidencias()
//    }
//
//    private fun updateIncidencias(){
//        val selectedEstados: List<EstadosIncidencias> = estados.filter { it.isSelected }
//        val nuevaIncidencia = incidencias.filter {selectedEstados.contains(it.estado)}
//        incidenciasAdapter.incidencias = nuevaIncidencia
//        incidenciasAdapter.notifyDataSetChanged()
//    }
}