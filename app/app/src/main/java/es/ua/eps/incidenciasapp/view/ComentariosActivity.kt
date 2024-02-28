package es.ua.eps.incidenciasapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.ua.eps.incidenciasapp.R
import es.ua.eps.incidenciasapp.databinding.ActivityComentariosBinding
import es.ua.eps.incidenciasapp.dataclass.Comentario

class ComentariosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComentariosBinding

    private lateinit var comentariosAdapter: ComentarioAdapter
    private var comentariosList: MutableList<Comentario> = mutableListOf()  // Inicializa la propiedad
    private lateinit var etNuevoComentario: EditText
    private var numIncidencia: Int = -1  // Inicializa con un valor predeterminado o adecuado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComentariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAceptarComentario.setOnClickListener(){
            finish()
        }
        numIncidencia = intent.getIntExtra("numIncidencia", -1)
        Log.d("ComentariosActivity", "numIncidencia: $numIncidencia")
        if (numIncidencia == -1) {
            // Manejar el caso en el que no se proporciona numIncidencia
            // Esto podría ser un error o un valor predeterminado según tus necesidades
            // Puedes mostrar un mensaje de error o finalizar la actividad en este caso
            finish()
            return
        }

        etNuevoComentario = findViewById(R.id.etNuevoComentario)
        val btnAgregarComentario = findViewById<Button>(R.id.btnAgregarComentario)
        val rvComentarios = findViewById<RecyclerView>(R.id.rvComentarios)

        comentariosList = obtenerComentariosDeSharedPreferences() // Obtener comentarios almacenados

        comentariosAdapter = ComentarioAdapter(comentariosList,  { comentario ->  eliminarComentario(comentario) })

        rvComentarios.adapter = comentariosAdapter
        rvComentarios.layoutManager = LinearLayoutManager(this)
        comentariosAdapter.setComentarios(comentariosList)
        comentariosAdapter.notifyDataSetChanged()

        btnAgregarComentario.setOnClickListener {
            val nuevoComentario = etNuevoComentario.text.toString()
            if (nuevoComentario.isNotEmpty()) {
                agregarComentario(nuevoComentario)
                etNuevoComentario.text.clear()
            }
        }
    }

    private fun eliminarComentario(comentario: Comentario) {
        // Elimina el comentario de la lista y actualiza el adaptador y SharedPreferences
        comentariosList.remove(comentario)
        guardarComentariosEnSharedPreferences(comentariosList)

        // Actualiza el adaptador y notifica los cambios
        comentariosAdapter.setComentarios(comentariosList)
        comentariosAdapter.notifyDataSetChanged()
    }

    private fun agregarComentario(contenido: String) {
        // Agregar el nuevo comentario a la lista y guardar en SharedPreferences
        val usuario = obtenerUsuarioActual()
        val comentario = Comentario(usuario, contenido)
        comentariosList.add(comentario)
        guardarComentariosEnSharedPreferences(comentariosList)

        runOnUiThread {
            comentariosList.clear()
            comentariosList.addAll(obtenerComentariosDeSharedPreferences())
            comentariosAdapter.setComentarios(comentariosList)
            comentariosAdapter.notifyDataSetChanged()
        }
    }

    private fun obtenerUsuarioActual(): String {
        // Lógica para obtener el usuario actual, puede ser el nombre del usuario
        // Aquí puedes utilizar las preferencias compartidas si ya tienes el nombre de usuario almacenado
        // o algún otro mecanismo para identificar al usuario
        return "UsuarioEjemplo"
    }

    private fun obtenerComentariosDeSharedPreferences(): MutableList<Comentario> {
        // Lógica para obtener la lista de comentarios asociada a la incidencia actual
        // Puedes utilizar SharedPreferences o algún otro mecanismo de almacenamiento
        // Retorna una lista vacía si no hay comentarios almacenados
        val sharedPreferences = getSharedPreferences("comentarios_incidencia_${numIncidencia}", Context.MODE_PRIVATE)
        val comentariosJson = sharedPreferences.getString("comentarios", "")
        Log.d("ComentariosActivity", "Comentarios cargados: ${comentariosList.size}")
        Log.d("ComentariosActivity", "Comentarios cargados: ${comentariosJson.orEmpty()}")
        return if (comentariosJson.isNullOrEmpty()) {
            mutableListOf()
        } else {
            Gson().fromJson(comentariosJson, object : TypeToken<List<Comentario>>() {}.type)
        }
    }

    private fun guardarComentariosEnSharedPreferences(comentarios: List<Comentario>) {
        // Lógica para guardar la lista de comentarios asociada a la incidencia actual en SharedPreferences
        val sharedPreferences = getSharedPreferences("comentarios_incidencia_${numIncidencia}", Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        val comentariosJson = Gson().toJson(comentarios)
        editor.putString("comentarios", comentariosJson)
        Log.d("ComentariosActivity", "Comentarios guardados: ${comentarios.size}")
        editor.apply()
    }
}