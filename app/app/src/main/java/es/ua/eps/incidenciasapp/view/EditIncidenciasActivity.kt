package es.ua.eps.incidenciasapp.view

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import es.ua.eps.incidenciasapp.databinding.ActivityEditIncidenciasBinding
import es.ua.eps.incidenciasapp.dataclass.Estado

class EditIncidenciasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditIncidenciasBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditIncidenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancelar.setOnClickListener {
            finish() // Cierra la actividad actual y vuelve a la anterior
        }

        binding.btnAceptar.setOnClickListener{
            showSnackbar("Guardando cambios")
            Handler().postDelayed({
                // Cambia a la nueva actividad
                val intent = Intent(this, VerIncidenciasActivity::class.java)
                startActivity(intent)

                // Si también deseas cerrar la actividad actual, puedes llamar a finish()
                // finish()
            }, 1000) // 1000 milisegundos (1 segundo) de retraso, puedes ajustar esto según tus necesidades
        }

        binding.btnAdjuntarArchivo.setOnClickListener(){
            adjuntarArchivo()
        }


        val estadosEnum = Estado.values()
        val estadosString = estadosEnum.map { it.name }
        val adapter = ArrayAdapter(this@EditIncidenciasActivity, R.layout.simple_spinner_item, estadosString)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = adapter
        val selectedEstado = Estado.valueOf(binding.spinner2.selectedItem.toString())


        val num = intent.getIntExtra("num", 0)
        val tipo = intent.getStringExtra("tipo")
        val subtipo=intent.getStringExtra("subtipo")
        val descripcion=intent.getStringExtra("descripcion")
        val fechaCreacion=intent.getStringExtra("fechaCreacion")
        val subsubtipo=intent.getStringExtra("subsubtipo")

        val aula=intent.getStringExtra("aula")
        val estado=intent.getStringExtra("estado")



        binding.edTipo.setText(tipo)
        binding.edFecha.setText(fechaCreacion.toString())
        binding.edFecha.isFocusable=false
        binding.edDescripcion.setText(descripcion)
        binding.edSubTipo.setText(subtipo + "," + subsubtipo)

            binding.edAula.setText(aula)



        if (estado.equals("abierta")){
            binding.spinner2.setSelection(0)
        }else if (estado.equals("asignada")){
            binding.spinner2.setSelection(1)
        }else if (estado.equals("en_proceso")){
            binding.spinner2.setSelection(2)
        }else if (estado.equals("enviado_a_infortec")){
            binding.spinner2.setSelection(3)
        }
        else if (estado.equals("resuelta")){
            binding.spinner2.setSelection(4)
        }else if (estado.equals("cerrada")){
            binding.spinner2.setSelection(5)
        }






    }

    private fun adjuntarArchivo() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"  // Todos los tipos de archivos
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, REQUEST_PICK_FILE)
    }

    companion object {
        private const val REQUEST_PICK_FILE = 123
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
    }