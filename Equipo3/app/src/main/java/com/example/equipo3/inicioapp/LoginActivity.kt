package com.example.equipo3.inicioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.equipo3.R
import com.example.equipo3.incidenciasapp.IncidenciasActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnAcceder)
        val logNombre = findViewById<EditText>(R.id.etNombre)
        val logPass = findViewById<EditText>(R.id.etPassword)

        btnLogin.setOnClickListener {
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
}