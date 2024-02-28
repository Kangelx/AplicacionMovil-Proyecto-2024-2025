package es.ua.eps.incidenciasapp.login


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import es.ua.eps.incidenciasapp.R
import es.ua.eps.incidenciasapp.databinding.ActivityMainBinding
import es.ua.eps.incidenciasapp.dataclass.DevolverTodoDTO
import es.ua.eps.incidenciasapp.dataclass.IncidenciaDTO
import es.ua.eps.incidenciasapp.dataclass.LoginData

import es.ua.eps.incidenciasapp.network.ApiService
import es.ua.eps.incidenciasapp.view.VerIncidenciasActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private val apiService = ApiClient.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tvOlvidarContrasena = findViewById<TextView>(R.id.tvOlvidarContrasena)
        val btnAcceder = findViewById<Button>(R.id.btnAcceder)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val checkBoxGuardarUsuario = findViewById<CheckBox>(R.id.checkBoxGuardarUsuario)


        val sharedPreferences =
            getSharedPreferences("mi_app_preferencias", Context.MODE_PRIVATE)
        checkBoxGuardarUsuario.isChecked = sharedPreferences.getBoolean("guardarUsuario", false)

        if (checkBoxGuardarUsuario.isChecked) {
            val savedUsername = sharedPreferences.getString("usuario", "")
            etNombre.setText(savedUsername)
        }

        btnAcceder.setOnClickListener {

            val username = findViewById<EditText>(R.id.etNombre).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()



            // Restaurar el estado del CheckBox desde las preferencias compartidas









            val loginData = LoginData(username, password)

            val call = apiService.login(loginData)

            call.enqueue(object : Callback<DevolverTodoDTO> {
                override fun onResponse(call: Call<DevolverTodoDTO>, response: Response<DevolverTodoDTO>) {
                    if (response.isSuccessful) {
                        val devolverTodoDTO = response.body()

                        val token = devolverTodoDTO?.token?.token
                        val usuarioId = devolverTodoDTO?.id
                        val listaIncidencias = devolverTodoDTO?.listaIncidencias

                        if (token != null) {
                            // Guarda el token y el usuarioId en las preferencias compartidas
                            guardarTokenYUsuarioEnPreferencias(token, usuarioId)


                            // Guarda la lista de incidencias en las preferencias compartidas
                            guardarIncidenciasEnPreferencias(listaIncidencias)

                            guardarUsuarioEnPreferencias(username)

                            // Agrega aquí la lógica para abrir la VerIncidenciasActivity
                            abrirVerIncidenciasActivity()


                        }
                    } else {
                        // Manejar error en el inicio de sesión
                        handleLoginError(response.code())
                    }
                }

                override fun onFailure(call: Call<DevolverTodoDTO>, t: Throwable) {
                    // Manejar fallo en la llamada
                    handleCallFailure(t)
                }
            })

            if (checkBoxGuardarUsuario.isChecked) {
                // Guardar el estado del CheckBox y el nombre de usuario en las preferencias compartidas
                val editor = sharedPreferences.edit()
                editor.putBoolean("guardarUsuario", true)
                editor.putString("usuario", username)
                editor.apply()
            } else {
                // Si no se selecciona guardar usuario, eliminar el nombre de usuario de las preferencias
                val editor = sharedPreferences.edit()
                editor.remove("guardarUsuario")
                editor.remove("usuario")
                editor.apply()
            }
        }


        tvOlvidarContrasena.setOnClickListener {
            // Intent para abrir Gmail (asegúrate de tener la aplicación de Gmail instalada)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/intl/es/gmail/about/"))
            startActivity(intent)
        }
    }

    private fun abrirVerIncidenciasActivity() {
        val intent = Intent(this, VerIncidenciasActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun handleLoginError(responseCode: Int) {
        // Manejar diferentes códigos de respuesta aquí
        Log.e("LoginActivity", "Error en el inicio de sesión: $responseCode")
        Toast.makeText(this@LoginActivity, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
    }

    private fun handleCallFailure(t: Throwable) {
        // Manejar fallos en la llamada aquí
        Log.e("LoginActivity", "Error en la llamada: ${t.message}")
        Toast.makeText(this@LoginActivity, "Error en la llamada", Toast.LENGTH_SHORT).show()
    }

    private fun guardarTokenYUsuarioEnPreferencias(token: String, usuarioId: Int?) {
        val sharedPreferences = getSharedPreferences("mi_app_preferencias", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("token", token)
        usuarioId?.let { editor.putInt("usuarioId", it) }

        editor.apply()
    }

    private fun guardarIncidenciasEnPreferencias(incidencias: List<IncidenciaDTO>?) {
        val sharedPreferences = getSharedPreferences("mi_app_preferencias", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val incidenciasJson = Gson().toJson(incidencias)
        editor.putString("incidencias", incidenciasJson)

        editor.apply()
    }

    private fun guardarUsuarioEnPreferencias(username: String) {
        val sharedPreferences = getSharedPreferences("mi_app_preferencias", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("usuario", username)

        editor.apply()
    }


}



