package es.ua.eps.incidenciasapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.ua.eps.incidenciasapp.R




class CategoryAdapter(
    private val context: Context,
    private val categories: List<TaskCategory>,
    private val onCategoryClickListener: (TaskCategory) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var selectedCategory: TaskCategory? = null
    private var onCategoryDeselectedListener: () -> Unit = {}
    fun setOnCategoryDeselectedListener(listener: () -> Unit) {
        onCategoryDeselectedListener = listener
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCategory: TextView = itemView.findViewById(R.id.tvCategoryName)
        val divider: View = itemView.findViewById(R.id.divider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_task_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        // Configurar el texto y el color del divisor según la categoría
        holder.textCategory.text = category.name

        if (category == selectedCategory) {
            // Categoría seleccionada, cambiar el color de fondo
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSelectedCategory))
        } else {
            // Categoría no seleccionada, usar el color normal
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        }

        holder.divider.setBackgroundColor(ContextCompat.getColor(context, category.colorResId))

        holder.itemView.setOnClickListener {
            if (selectedCategory == category) {
                // Categoría ya seleccionada, deseleccionarla
                selectedCategory = null
                notifyDataSetChanged()
                onCategoryDeselectedListener.invoke()
            } else {
                // Categoría no seleccionada, seleccionarla
                selectedCategory = category
                notifyDataSetChanged()
                onCategoryClickListener(category)
            }
        }
    }


    override fun getItemCount(): Int {
        return categories.size
    }
}
