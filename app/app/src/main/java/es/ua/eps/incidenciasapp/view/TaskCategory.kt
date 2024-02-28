package es.ua.eps.incidenciasapp.view

import androidx.core.content.ContextCompat.getColor
import es.ua.eps.incidenciasapp.R

sealed class TaskCategory(val name: String, val colorResId: Int) {
    class Abierto : TaskCategory("Abierto", R.color.colorNaranja)
    class Asignado : TaskCategory("Asignado", R.color.colorVerde)
    class EnProceso : TaskCategory("En proceso", R.color.colorRojo)
    class EnviadoAInfortec : TaskCategory("Enviado a Infortec", R.color.colorPrimaryDark)
    class Resuelto : TaskCategory("Resuelto", R.color.colorMarron)
    class Cerrado : TaskCategory("Cerrado", R.color.colorMorado)
}