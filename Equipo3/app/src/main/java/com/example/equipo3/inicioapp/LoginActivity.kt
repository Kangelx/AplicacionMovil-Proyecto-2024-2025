package com.example.equipo3.inicioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast

import com.example.equipo3.ApiService.ApiService
import com.example.equipo3.R
import com.example.equipo3.databinding.ActivityLoginBinding
import com.example.equipo3.incidenciasapp.IncidenciasActivity
import com.example.equipo3.model.UsuarioData
import com.example.equipo3.response.ProfesorDataResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()


        val btnLogin = findViewById<Button>(R.id.btnAcceder)
        btnLogin.setOnClickListener {
            if(validarDatos()){
                var buscaEmail = binding.etNombre.text.toString()
                var buscaContra = binding.etNombre.text.toString()

                val user = UsuarioData(buscaEmail, buscaContra)
                searchByEmail(user)
            }
       }
    }

    private fun searchByEmail(query: UsuarioData){
        CoroutineScope(Dispatchers.IO).launch{
            val myResponse = retrofit.create(ApiService::class.java).getProfesor(query)
            if(myResponse.isSuccessful){
                Log.i("AlpacApp", "FuncionApp")
                val response: ProfesorDataResponse? = myResponse.body()
                if(response !=null){
                    Log.i("AlpacApp", response.toString())
                    verIncidencias()
                }
            } else {
                Log.i("Alpacapp", "No funcionApp")
            }
        }
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("http://10.0.13.119:4001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
        private fun validarDatos() : Boolean{
//        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etNombre.text.toString()).matches()){
//            Toast.makeText(this, "El correo indicado no es válido", Toast.LENGTH_LONG).show()
//            return false
//        }

        if(binding.etNombre.text.toString().isEmpty()){
            Toast.makeText(this, "Indica el correo del profesor.", Toast.LENGTH_LONG).show()
            return false
        }

        if(binding.etPassword.text.toString().isEmpty()){
            Toast.makeText(this, "Indica la contraseña.", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun verIncidencias(){
        val btnLogin = findViewById<Button>(R.id.btnAcceder)
        btnLogin.setOnClickListener {
            val incidencias = Intent(this, IncidenciasActivity::class.java)
            startActivity(incidencias)
        }
    }
}