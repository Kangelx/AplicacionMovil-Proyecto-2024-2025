package es.ua.eps.incidenciasapp.dataclass

// LoginData.kt


data class LoginData(
    val username: String,
    val password: String
)

// DevolverTodoDTO.kt


data class DevolverTodoDTO(
    val nombre: String?,
    val token: AuthTokenResponse?,
    val listaIncidencias: List<IncidenciaDTO>?,
    val id: Int?
)

data class AuthTokenResponse(
    val token: String
)

data class IncidenciaDTO(
    val num: Int,
    val tipo: String,
    val subtipo_id: SubtipoId,
    val fechaCreacion: String,
    val fechaCierre: String,
    val descripcion: String,
    val estado: String,
    val adjuntoUrl: String,
    val creador_id: Persona,
    val responsable_id: Persona,
    val equipo_id: Equipo,
    val prioridad: String
)

data class SubtipoId(
    val id: Int,
    val tipo: String,
    val subtipoNombre: String,
    val subSubtipo: Any? // Puedes ajustar el tipo de datos según la estructura real
)

data class Persona(
    val id: Int,
    val dni: String,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val direccion: String,
    val localidad: String,
    val cp: String?, // Puede ser nullable si el campo es opcional
    val tlf: String,
    val activo: Int,
    val departamentoId: Departamento
)

data class Departamento(
    val id: Int,
    val cod: String,
    val nombre: String,
    val activo: Int
)

data class Equipo(
    val id: Int,
    val tipoEquipo: String,
    val fechaAdquisicion: String,
    val etiqueta: String,
    val marca: String,
    val modelo: String,
    val descripcion: String,
    val baja: Int,
    val aula_num: AulaNum,
    val puesto: Int
)

data class AulaNum(
    val num: Int,
    val codigo: String,
    val descripcion: String,
    val planta: Int
)