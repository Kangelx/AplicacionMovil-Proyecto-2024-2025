package layout

data class Incidencia(
    val num: Int?,
    val tipo: String,
    val subtipoId: Subtipo,
    val fechaCreacion: String,
    val fechaCierre: String?,
    val descripcion: String,
    val estado: String,
    val adjuntoUrl: String?,

    val equipoId: Equipo?,
    val prioridad: String
) {
    data class Subtipo(
        val id: Int,
        val tipo: String,
        val subtipoNombre: String,
        val subSubtipo: Any? // Puedes ajustar esto según la estructura real
    )



    data class Equipo(
        // Estructura de la clase Equipo
        val aula_num: Aula
    )

    data class Aula(
        val num: Int,
        val codigo: String,
        val descripcion: String,
        val planta: Int
    )
}
