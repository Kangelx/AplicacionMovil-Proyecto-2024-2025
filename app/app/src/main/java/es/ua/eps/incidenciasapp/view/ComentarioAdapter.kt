package es.ua.eps.incidenciasapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.incidenciasapp.R
import es.ua.eps.incidenciasapp.dataclass.Comentario

class ComentarioAdapter(comentariosList: MutableList<Comentario>,
                        private val onDeleteClickListener: (Comentario) -> Unit) : RecyclerView.Adapter<ComentarioAdapter.ViewHolder>() {

    private var comentarios: List<Comentario> = ArrayList()

    fun setComentarios(comentarios: List<Comentario>) {
        this.comentarios = comentarios
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comentario = comentarios[position]
        holder.bind(comentario)
        holder.btnDelete.setOnClickListener {
            onDeleteClickListener(comentario)
        }
    }

    override fun getItemCount(): Int {
        return comentarios.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvComentario: TextView = itemView.findViewById(R.id.tvComentario)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnEliminarComentario)
        fun bind(comentario: Comentario) {
            tvComentario.text = comentario.contenido
        }
    }
}