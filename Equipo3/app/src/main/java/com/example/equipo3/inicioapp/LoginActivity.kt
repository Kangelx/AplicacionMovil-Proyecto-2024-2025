package com.example.equipo3.inicioapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.equipo3.ApiService.ApiClient

import com.example.equipo3.ApiService.ApiService
import com.example.equipo3.R
import com.example.equipo3.databinding.ActivityLoginBinding
import com.example.equipo3.incidenciasapp.IncidenciasActivity
import com.example.equipo3.model.UsuarioData
import com.example.equipo3.response.ProfesorDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
//    private val apiService = ApiClient.create()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        val btnAcceder = findViewById<Button>(R.id.btnAcceder)
//        btnAcceder.setOnClickListener {
//            val username = findViewById<EditText>(R.id.etNombre).text.toString()
//            val password = findViewById<EditText>(R.id.etPassword).text.toString()
//
//            val loginData = UsuarioData(username, password)
//
//            val call = apiService.login(loginData)

//            call.enqueue(object : Callback<ProfesorDataResponse> {
//                override fun onResponse(call: Call<ProfesorDataResponse>, response: Response<ProfesorDataResponse>) {
//                    if (response.isSuccessful) {
//                        val devolverTodoDTO = response.body()
//
//                        // Asegúrate de que devolverTodoDTO no sea nulo y contenga el token
//                        val token = devolverTodoDTO?.token?.token
//                        val usuarioId=devolverTodoDTO?.personalID
//
//                        if (token != null) {
//                            // Guarda el token en las preferencias compartidas
//                            guardarTokenEnPreferencias(token)
//                            Log.d("LoginActivity", "Inicio de sesión exitoso")
//                            if (usuarioId != null) {
//                                guardarUsuarioEnPreferencias(usuarioId)
//                            }
//
//                            // guardarIncidenciasEnPreferencias(devolverTodoDTO.listaIncidencias)
//                            // Agrega aquí la lógica para abrir la VerIncidenciasActivity
//                            abrirVerIncidenciasActivity()
//                        }
//
//
//
//                    } else {
//                        Log.d("LoginActivity", "Credenciales - Usuario: $username, Contraseña: $password")
//
//                        Log.e("LoginActivity", "Error en el inicio de sesión: ${response.code()}")
//                        // Aquí puedes manejar la respuesta de error
//                        Toast.makeText(this@LoginActivity, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
//                    }
//                }

//                override fun onFailure(call: Call<ProfesorDataResponse>, t: Throwable) {
//                    // Aquí puedes manejar el fallo en la llamada
//                    Log.e("LoginActivity", "Error en la llamada: ${t.message}")
//                    Toast.makeText(this@LoginActivity, "Error en la llamada", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//    }

//    private fun abrirVerIncidenciasActivity() {
//        val intent = Intent(this, IncidenciasActivity::class.java)
//        startActivity(intent)
//        finish() // Esto evita que el usuario pueda volver atrás con el botón de retroceso
//    }
//
//    private fun guardarTokenEnPreferencias(token: String) {
//        // Obtener el objeto SharedPreferences
//        val sharedPreferences = getSharedPreferences("mi_app_preferencias", Context.MODE_PRIVATE)
//
//        val editor = sharedPreferences.edit()
//        editor.putString("token", token)
//
//
//        editor.apply()
//    }
//
//    private fun guardarUsuarioEnPreferencias(id:Int) {
//        val sharedPreferences = getSharedPreferences("mi_app_preferencias", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putInt("usuarioId", id)
//        editor.apply()
//    }

//    private lateinit var binding: ActivityLoginBinding
//    private lateinit var retrofit: Retrofit
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        retrofit = getRetrofit()
//
//
//        val btnLogin = findViewById<Button>(R.id.btnAcceder)
//        btnLogin.setOnClickListener {
//            if(validarDatos()){
//                var buscaEmail = binding.etNombre.text.toString()
//                var buscaContra = binding.etNombre.text.toString()
//
//                val user = UsuarioData(buscaEmail, buscaContra)
//                searchByEmail(user)
//            }
//       }
//    }
//
//    private fun searchByEmail(query: UsuarioData){
//        CoroutineScope(Dispatchers.IO).launch{
//            val myResponse = retrofit.create(ApiService::class.java).getProfesor(query)
//            if(myResponse.isSuccessful){
//                Log.i("AlpacApp", "FuncionApp")
//                val response: ProfesorDataResponse? = myResponse.body()
//                if(response !=null){
//                    Log.i("AlpacApp", response.toString())
//                    verIncidencias()
//                }
//            } else {
//                Log.i("Alpacapp", "No funcionApp")
//            }
//        }
//    }
//
//    private fun getRetrofit(): Retrofit{
//        return Retrofit
//            .Builder()
//            .baseUrl("http://10.0.13.119:4001/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//        private fun validarDatos() : Boolean{
////        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etNombre.text.toString()).matches()){
////            Toast.makeText(this, "El correo indicado no es válido", Toast.LENGTH_LONG).show()
////            return false
////        }
//
//        if(binding.etNombre.text.toString().isEmpty()){
//            Toast.makeText(this, "Indica el correo del profesor.", Toast.LENGTH_LONG).show()
//            return false
//        }
//
//        if(binding.etPassword.text.toString().isEmpty()){
//            Toast.makeText(this, "Indica la contraseña.", Toast.LENGTH_LONG).show()
//            return false
//        }
//
//        return true
//    }
//
//    private fun verIncidencias(){
//        val btnLogin = findViewById<Button>(R.id.btnAcceder)
//        btnLogin.setOnClickListener {
//            val incidencias = Intent(this, IncidenciasActivity::class.java)
//            startActivity(incidencias)
//        }
//    }
}