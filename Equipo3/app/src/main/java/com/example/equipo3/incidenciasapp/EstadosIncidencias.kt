package com.example.equipo3.incidenciasapp

import com.example.equipo3.R

sealed class EstadosIncidencias (val name: String, val colorResId: Int){
    class Abierto : EstadosIncidencias("Abierto", R.color.estado_abierto)
    class Asignado : EstadosIncidencias("Asignado", R.color.estado_asignado)
    class EnProceso : EstadosIncidencias("En proceso", R.color.estado_enProceso)
    class EnviadoAInfortec : EstadosIncidencias("Enviado a Infortec", R.color.estado_enviadoInfortec)
    class Resuelto : EstadosIncidencias("Resuelto", R.color.estado_resuelto)
    class Cerrado : EstadosIncidencias("Cerrado", R.color.estado_cerrado)
//    object Abierto: EstadosIncidencias()
//    object Asignado: EstadosIncidencias()
//    object EnProceso: EstadosIncidencias()
//    object EnviadoInfortec: EstadosIncidencias()
//    object Resuelto: EstadosIncidencias()
//    object Cerrado: EstadosIncidencias()
}