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
import com.example.equipo3.viewmodel.UsuarioViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var repositorio: Repository

    private lateinit var btnLogin :Button
    private lateinit var logNombre: EditText
    private lateinit var logPass: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initcomponent()

        btnLogin.setOnClickListener {
        //verIncidencias()
            login()
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
            */
        }
    }

    private fun isValorPassword(password: String): Boolean = password.length > 6

    private fun isValorNombre(nombre: String): Boolean{
        var correcto: Boolean
        if (nombre.length>3 && nombre.length<32){
            correcto = true
        }
        else {
            correcto = false
        }
        return correcto
    }

    private fun existeUsuario(user: String): Boolean{
        val existe = false

        return existe
    }

    private fun verIncidencias(){
        val incidencias = Intent(this, IncidenciasActivity::class.java)
        startActivity(incidencias)
    }

    fun login(){

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val usuario = binding.etNombre.text.toString()
        getUser(usuario)
            if (validarDatos()) {

                val pass = hashMD5(binding.etPassword.text.toString())


                lifecycleScope.launch {
                    try {
                        val usuario = repositorio.getUsuario(usuario)

                        verIncidencias()
                    } catch (e: HttpException) {
                        //Toast.makeText(this, "Indica el correo del profesor.", Toast.LENGTH_LONG).show()
                    }
                }
            }

    }
    private fun validarDatos() : Boolean{
        if(binding.etNombre.text.toString().isEmpty()){
            Toast.makeText(this, "Indica el correo del profesor.", Toast.LENGTH_LONG).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etNombre.text.toString()).matches()){
            Toast.makeText(this, "El correo indicado no es válido", Toast.LENGTH_LONG).show()
            return false
        }

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

    private fun hashMD5(pass: String) : String {
        val md = MessageDigest.getInstance("MD5")
        val digesto = md.digest(pass.toByteArray())
        return digesto.joinToString(""){
            String.format("%02x",it)
        }
    }

    private fun initUI(){

    }

    private fun initcomponent(){
        btnLogin = findViewById<Button>(R.id.btnAcceder)
        logNombre = findViewById<EditText>(R.id.etNombre)
        logPass = findViewById<EditText>(R.id.etPassword)
    }

    private fun getUser(email: String){
        CoroutineScope(Dispatchers.IO).launch {
            val usuario =
                getRetrofit().create(ApiService::class.java).getUsuario(email)
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("http://10.0.13.119:4001/perfil/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}