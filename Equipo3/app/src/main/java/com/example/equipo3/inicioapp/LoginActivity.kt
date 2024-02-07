package com.example.equipo3.inicioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.equipo3.R

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

            if(nombre.isNotEmpty() && pass.isNotEmpty()){
                if(nombre.length>3 && nombre.length<32){

                }

            }else{
                //sout"Error usuario o contraseña están en blanco"
            }

        }
    }
}