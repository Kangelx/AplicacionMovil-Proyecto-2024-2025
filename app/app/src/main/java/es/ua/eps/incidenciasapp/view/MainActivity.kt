package es.ua.eps.incidenciasapp.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.incidenciasapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura el click listener para el botón
        binding.btnAcceder.setOnClickListener {
            // Abre la nueva actividad
            val intent = Intent(this, VerIncidenciasActivity::class.java)
            startActivity(intent)
        }


    }
}