package com.example.equipo3.inicioapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.lifecycle.lifecycleScope
import com.example.equipo3.ApiService.ApiService
import com.example.equipo3.R
import com.example.equipo3.databinding.ActivityLoginBinding
import com.example.equipo3.incidenciasapp.IncidenciasActivity
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
    private lateinit var repositorio: Repository

    private lateinit var btnLogin :Button
    private lateinit var logNombre: EditText
    private lateinit var logPass: EditText

    private val apiService: ApiService by lazy {
        ApiService.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["nombre", ""].contains("."))
            verIncidencias()

        initcomponent()

        btnLogin.setOnClickListener {
            performLogin()
            //login()
            /*
            val nombre = logNombre.text.toString()
            val pass = logPass.text.toString()


            if(isValorNombre(nombre) && isValorPassword(pass)){
                verIncidencias()
            }else if(!isValorNombre(nombre)){
                Toast.makeText(this, "Error, usuario incorrecto", Toast.LENGTH_LONG).show()
            }
            else if(!isValorPassword(pass)){
                Toast.makeText(this, "Error, contraseña incorrecta", Toast.LENGTH_LONG).show()
            }
           // */
       }
    }

    private fun performLogin(){
        val etEmail = findViewById<EditText>(R.id.etNombre).text.toString()
        val etPass = findViewById<EditText>(R.id.etPassword).text.toString()

        val call = apiService.postLogin(etEmail, etPass)
        call.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    val loginResponse = response.body()
                    if (loginResponse == null){
                        Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_LONG).show()
                        return
                    }
                    if (loginResponse.nombre.isNotEmpty()){
                        createSessionPreference(loginResponse.nombre)
                        verIncidencias()
                    } else {
                        Toast.makeText(applicationContext, "El e-mail o la contraseña son incorrectos", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun createSessionPreference(nombre: String){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["nombre"] = nombre
    }

    private fun verIncidencias(){
        val incidencias = Intent(this, IncidenciasActivity::class.java)
        startActivity(incidencias)
    }

//    fun login(){
//
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        val usuario = binding.etNombre.text.toString()
//        getUser(usuario)
//            if (validarDatos()) {
//
//                val pass = hashMD5(binding.etPassword.text.toString())
//
//
//                lifecycleScope.launch {
//                    try {
//                        val usuario = repositorio.getUsuario(usuario)
//
//                        verIncidencias()
//                    } catch (e: HttpException) {
//                        //Toast.makeText(this, "Indica el correo del profesor.", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//
//    }
//    private fun validarDatos() : Boolean{
//        if(binding.etNombre.text.toString().isEmpty()){
//            Toast.makeText(this, "Indica el correo del profesor.", Toast.LENGTH_LONG).show()
//            return false
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etNombre.text.toString()).matches()){
//            Toast.makeText(this, "El correo indicado no es válido", Toast.LENGTH_LONG).show()
//            return false
//        }
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
//    private fun hashMD5(pass: String) : String {
//        val md = MessageDigest.getInstance("MD5")
//        val digesto = md.digest(pass.toByteArray())
//        return digesto.joinToString(""){
//            String.format("%02x",it)
//        }
//    }
//
//    private fun initUI(){
//
//    }

    private fun initcomponent(){
        btnLogin = findViewById<Button>(R.id.btnAcceder)
        logNombre = findViewById<EditText>(R.id.etNombre)
        logPass = findViewById<EditText>(R.id.etPassword)
    }

//    private fun getUser(email: String){
//        CoroutineScope(Dispatchers.IO).launch {
//            val usuario =
//                getRetrofit().create(ApiService::class.java).getUsuario(email)
//        }
//    }

//    private fun getRetrofit(): Retrofit {
//        return Retrofit
//            .Builder()
//            .baseUrl("http://10.0.13.119:4001/perfil/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
}