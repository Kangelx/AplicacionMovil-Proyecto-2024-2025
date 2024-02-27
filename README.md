# AlpacApp Incidents

## **Índice de contenido**
- [Proyecto intermodular](#proyecto-intermodular)
  - [**Índice de contenido**](#índice-de-contenido)
  - [**Introducción**](#introducción)
  - [**Aplicación móvil**](#aplicación-móvil)
    - [*Descripción*](#descripción)
    - [*Funcionalidades*](#funcionalidades)
      - [Login del profesor](#login-del-profesor)
      - [Pantalla de inicio](#pantalla-de-inicio)
      - [Ver incidencia](#ver-incidencia)
      - [Generar incidencia](#generar-incidencia)
      - [Ajustes](#ajustes)
  - [**Aplicación de escritorio**](#aplicación-de-escritorio)
  - [**Base de datos**](#base-de-datos)
  - [**Documentación**](#documentación)
  - [**SGE**](#sge)
  - [**Autores del proyecto**](#autores-del-proyecto)


## **Introducción**

Proyecto para le gestión de incidencias realizadas por el departamento TIC del centro IES Miguel Herrero

El resultado de este proyecto son dos aplicaciones, una móvil y otra de escritorio, que, a través de una API que se conecte a la base de datos pertinente, consiguen gestionar las incidencias realizadas por profesores del centro.

---

## **Aplicación móvil**
### *Descripción*
La aplicación móvil está diseñada para Android y está programada en Kotlin con AndriodStudio Giraffe | 2022.3.1 Patch 3.

### *Funcionalidades*
#### Login del profesor

![login](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/2fe0a7cc-4fa4-4d42-84d1-e92f52aab6b9)


El login es la primera pantalla que verá el usuario al acceder a la aplicación.

En esta pantalla el usuario tiene la opción de marcar la casilla para recordar el usuario y así no tener que volver a escribir este dato a la hora de volver a iniciar sesión. En el caso de que el usuario haya olvidado la contraseña también va a tener una opción para recuperarla, por lo tanto al pulsar el enlace recibirá un correo.

<p align="center"><img src="recuperarpass.img"/></p>

En caso de que el usuario o la contraseña sean incorrectos verá un toast que indique que los datos son incorrectos.

<p align="center"><img src = "errorlogin.img"/></p>

#### Pantalla de inicio

<p align="center"><img src="inicio.img"/></p>

En la pantalla principal el usuario podrá ver una casilla de búsqueda, una lista horizontal de categorías, una lista vertical de inicidencias, un botón con datos de usuario y un botón para agregar incidencia.

En el searchbox o casilla de búsqueda, el usuario podrá poner un número de incidencia y, en caso de que ésta exista, aparecerá filtrada la incidencia con dicho número en la lista de incidencias.

<p align="center"><img src="incidencia_filtrada.img"/></p>

En la lista horizontal están las siguientes categorías: abierto, asignado, en proceso, enviado a infortec, resuelto y cerrado. Cada categoría tiene asignado un color para que visualmente sean más fácilmente identificables. Al principio están todas las categorías desmarcadas para mostrar todas las incidencias de todas las categorías. Al pinchar sobre una categoría el listado realizará un filtrado y mostrará las incidencias que tengan asignadas dicha categoría, pudiendo elegir varias categorías a la vez. Si se vuelve a pinchar, esta selección se desmarcará.

<p align="center"><img src="categoria_seleccionada.img"/></p>

En la lista horizontal de incidencias el usuario verá un cardview con información básica de la incidencia: número de incidencia, tipo de incidencia y descripción de la misma. Abajo a la izquierda tendrá dos botones, uno para borrar incidencia y otro para añadir comentario que al pulsar abrirá una nueva ventana que permitirá al usuario escribir un comentario o borrar comentarios anteriores.

<p align="center"><img src="comentarios.img"/></p>

Por último, abajo a la derecha hay un pequeño cuadrado con el color correspondiente a la categoría de la incidencia para saber el estado de la misma

#### Ver incidencia
<p align="center"><img src="datos_incidencia"/></p>



#### Generar incidencia
<p align="center"><img src=""/></p>



<p align="center"><img src=""/></p>



<p align="center"><img src=""/></p>


#### Ajustes
<p align="center"><img src="ajustes.img"/></p>

En la pantalla de ajustes el usuario podrá activar o desactivar el modo oscuro, activar o desactivar el Bluetooth, subir o bajar el volumen, activar o desactivar el modo vibración del dispositivo. También se mostrará el nombre del usuario y dos botones que permitirán cerrar sesión o guardar los cambios realizados.

---

## [Aplicación de escritorio](https://github.com/Kangelx/AplicacionEscritorio-Proyecto-2024-2025/blob/main/README.md "Aplicación de escritorio")

---

## [Base de datos](https://github.com/Kangelx/Base-de-datos-Proyecto-2024-2025/blob/main/README.md "Base de datos")

---

## [Documentacion](https://github.com/Kangelx/Documentacion-Proyecto-2024-2025 "Documentación")

---

## [SGE](https://github.com/Kangelx/SGE-Proyecto-2024/blob/main/README.md "SGE")

---

## **Autores del proyecto**
### *Aplicación móvil*
- :iphone: Diego Corominas Gómez :iphone:
- :llama: Oana Irina Cutitaru Ciobanu :llama:

### *Aplicación de escritorio*
- :headphones: Paula Díaz Santos :headphones:
- :desktop_computer: Ángel García García :desktop_computer:

### *Python*
- :snake: Rubén Cortés Muñoz :snake:

### *Base de datos*
- :file_folder: Pablo Sainz Luque :file_folder:
