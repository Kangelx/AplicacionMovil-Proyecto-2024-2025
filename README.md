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



En caso de que el usuario o la contraseña sean incorrectos verá un toast que indique que los datos son incorrectos.



#### Pantalla de inicio

![inicio](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/ece3ceb1-f1c1-4606-88d1-b3104e838683)


En la pantalla principal el usuario podrá ver una casilla de búsqueda, una lista horizontal de categorías, una lista vertical de inicidencias, un botón con datos de usuario y un botón para agregar incidencia.

En el *searchbox* o casilla de búsqueda, el usuario podrá poner un número de incidencia y, en caso de que ésta exista, aparecerá filtrada la incidencia con dicho número en la lista de incidencias.

![incidencia_filtrada](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/a71d1cbd-e041-4430-bdcf-63beed7400d3)


En la lista horizontal están las siguientes categorías: abierto, asignado, en proceso, enviado a infortec, resuelto y cerrado. Cada categoría tiene asignado un color para que visualmente sean más fácilmente identificables. Al principio están todas las categorías desmarcadas para mostrar todas las incidencias de todas las categorías. Al pinchar sobre una categoría el listado realizará un filtrado y mostrará las incidencias que tengan asignadas dicha categoría, pudiendo elegir varias categorías a la vez. Si se vuelve a pinchar, esta selección se desmarcará.

![categoria_seleccionada](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/ead0d04f-e508-455d-b9a4-749151b8f51e)

En la lista horizontal de incidencias el usuario verá un *cardview* con información básica de la incidencia: número de incidencia, tipo de incidencia y descripción de la misma. Abajo a la izquierda tendrá dos botones, uno para borrar incidencia y otro para añadir comentario que al pulsar abrirá una nueva ventana que permitirá al usuario escribir un comentario o borrar comentarios anteriores.

![comentarios](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/769ffe6c-1be6-4fdb-a7b9-93845b91d9d6)

Por último, abajo a la derecha hay un pequeño cuadrado con el color correspondiente a la categoría de la incidencia para saber el estado de la misma

#### Ver incidencia
![datos_incidencia](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/8c5697fd-36c2-41fc-b068-b651f1bf4125)

Al pulsar sobre una incidencia dentro de la lista de incidencias se abrirá una nueva vista con el logo de AlpacApp, donde se verá la siguiente información: tipo de incidencia, descripción de la misma, fecha de creación, aula, subtipo y estado. Esta información se podrá modificar.

El usuario podrá adjuntar un archivo, en cuyo caso se abrirá una ventana de explorador para poder elegir el archivo que se quiera adjuntar.

![adjuntar](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/35c549f6-608d-443c-a58e-473c81642618)

Por último, en caso de que se quieran descartar los cambios que se han realizado, el usuario tendrá a su disposición el botón *cancelar* y, si por el contrario quiere guardar dichos cambios, podrá hacer uso del botón *aceptar*.

#### Generar incidencia
![nueva_incidencia](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/86711b23-9a50-4f5c-a682-66d3ae261a2f)

Al elegir crear una nueva incidencia se abrirá una vista parecida a la anterior pero con los campos sin rellenar. En esta vista el usuario deberá editar los campos que se le muestran: tipo de incidencia, descripción de la misma, fecha de creación, aula, subtipo y estado.

En caso de que se arrepienta y no quiera guardar esta incidencia deberá optar por pulsar el botón *cancelar*, mientras que si está de acuerdo con los datos indicados y quiera subir esta incidencia a la base de datos deberá pulsar el botón *aceptar*




#### Ajustes
![ajustes](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/7cfe9c80-53cc-47de-9dda-6f058cfa249f)


En la pantalla de ajustes el usuario podrá activar o desactivar el modo oscuro, activar o desactivar el Bluetooth, subir o bajar el volumen, activar o desactivar el modo vibración del dispositivo. También se mostrará el nombre del usuario y dos botones que permitirán cerrar sesión o guardar los cambios realizados.

![modo oscuro](https://github.com/Kangelx/AplicacionMovil-Proyecto-2024-2025/assets/92816033/021bad95-9b25-4a9a-8a2d-93e4212d8ae8)

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
