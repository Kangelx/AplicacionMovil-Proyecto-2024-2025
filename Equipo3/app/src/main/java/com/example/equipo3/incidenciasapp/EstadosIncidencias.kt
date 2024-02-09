package com.example.equipo3.incidenciasapp

sealed class EstadosIncidencias (var isSelected:Boolean = true){
    object Abierto: EstadosIncidencias()
    object Asignado: EstadosIncidencias()
    object EnProceso: EstadosIncidencias()
    object EnviadoInfortec: EstadosIncidencias()
    object Resuelto: EstadosIncidencias()
    object Cerrado: EstadosIncidencias()
}