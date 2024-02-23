package com.example.equipo3.inicioapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.lifecycle.lifecycleScope
import com.example.equipo3.ApiService.ApiService
import com.example.equipo3.R
import com.example.equipo3.databinding.ActivityLoginBinding
import com.example.equipo3.incidenciasapp.IncidenciasActivity
import com.example.equipo3.model.ProfesorDataResponse
import com.example.equipo3.model.Repository
import com.example.equipo3.response.LoginResponse
import com.example.equipo3.util.PreferenceHelper
import com.example.equipo3.util.PreferenceHelper.get
import com.example.equipo3.util.PreferenceHelper.set
import com.example.equipo3.viewmodel.UsuarioViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
//        initUI()


//        val preferences = PreferenceHelper.defaultPrefs(this)
//        if (preferences["nombre", ""].contains("."))
//            verIncidencias()

//        initcomponent()

        val btnLogin = findViewById<Button>(R.id.btnAcceder)
        btnLogin.setOnClickListener {
            if(validarDatos()){
                var busca = binding.etNombre.text.toString()
                searchByEmail(busca)
            }

       }


    }

//    private fun initUI(){
//        var busca = binding.etNombre.text.toString()
//        if (busca.isNotEmpty()){
//            searchByEmail(busca)
//        }
//
//    }
    private fun searchByEmail(query: String){
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