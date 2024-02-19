package com.example.equipo3.viewmodel


import androidx.lifecycle.ViewModel
import com.example.equipo3.model.Repository

class UsuarioViewModel : ViewModel() {
    private val repositorio = Repository()

    suspend fun cargarUsuario(user: String, password: String) = repositorio.getUsuario(user, password)
}