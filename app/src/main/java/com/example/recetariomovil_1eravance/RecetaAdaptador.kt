package com.example.recetariomovil_1eravance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecetaAdaptador(private var recetas: List<Receta>) : RecyclerView.Adapter<RecetaAdaptador.RecetaViewHolder>() {



    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val currentReceta = recetas[position]
        holder.nombreTextView.text = currentReceta.nombre
        holder.ingredientesTextView.text = currentReceta.ingredientes.joinToString(", ")
        holder.tiempoTextView.text = currentReceta.tiempoPreparacion
        holder.dificultadTextView.text = currentReceta.dificultad
        holder.tipoDietaTextView.text = currentReceta.tipoDieta
        holder.procedimientoTextView.text = currentReceta.procedimientos.toString()

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, currentReceta)
            }
        }
    }

    override fun getItemCount() = recetas.size

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Receta)
    }

    fun Recetas(recetas: List<Receta>){
        this.recetas = recetas
        notifyDataSetChanged()
    }

    class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val ingredientesTextView: TextView = itemView.findViewById(R.id.ingredientesTextView)
        val tiempoTextView: TextView = itemView.findViewById(R.id.tiempoTextView)
        val dificultadTextView: TextView = itemView.findViewById(R.id.dificultadTextView)
        val tipoDietaTextView: TextView = itemView.findViewById(R.id.tipoDietaTextView)
        val procedimientoTextView: TextView = itemView.findViewById(R.id.procedimientoTextView)
    }

}

