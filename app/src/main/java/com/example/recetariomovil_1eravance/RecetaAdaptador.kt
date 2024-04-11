package com.example.recetariomovil_1eravance

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecetaAdaptador(private val recetas: List<Receta>) : RecyclerView.Adapter<RecetaAdaptador.RecetaViewHolder>() {

    class RecetaViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val textView = TextView(parent.context)
        return RecetaViewHolder(textView)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        holder.textView.text = recetas[position].nombre
    }

    override fun getItemCount() = recetas.size
}
