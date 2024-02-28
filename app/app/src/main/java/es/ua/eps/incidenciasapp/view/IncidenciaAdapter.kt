package layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.incidenciasapp.R
import es.ua.eps.incidenciasapp.databinding.ItemIncidenciaBinding


class IncidenciaAdapter (private val clickListener: (Incidencia) -> Unit,
                         private val deleteClickListener: (Incidencia) -> Unit,
                         private val commentsClickListener: (Incidencia) -> Unit): RecyclerView.Adapter<IncidenciaAdapter.ViewHolder>() {

    private var incidencias: List<Incidencia> = ArrayList()



    fun setIncidencias(incidencias: List<Incidencia>) {
        this.incidencias = incidencias
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIncidenciaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val incidencia = incidencias[position]
        holder.bind(incidencia)
        holder.itemView.setOnClickListener { clickListener(incidencia) }
        holder.binding.btnBorrar.setOnClickListener { deleteClickListener(incidencia) }
        holder.binding.btnComentarios.setOnClickListener { commentsClickListener(incidencia) }
    }

    override fun getItemCount(): Int {
        return incidencias.size
    }

    class ViewHolder(val binding: ItemIncidenciaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(incidencia: Incidencia) {
            binding.numTextView.text = incidencia.num.toString()
            binding.tipoTextView.text = incidencia.tipo
            binding.descripcionTextView.text = incidencia.descripcion

            val colorResId = when (incidencia.estado) {
                "abierta" -> R.color.colorNaranja
                "asignada" -> R.color.colorVerde
                "resuelta" -> R.color.colorMarron
                "en_proceso" -> R.color.colorRojo
                "cerrada"-> R.color.colorMorado
                "enviada_a_Infortec" -> R.color.colorPrimaryDark

                // Agrega más casos según sea necesario
                else -> R.color.colorGrisClaro// Color predeterminado si el estado no coincide
            }
            binding.colorIndicator.setBackgroundColor(ContextCompat.getColor(binding.root.context, colorResId))
        }
    }
}
