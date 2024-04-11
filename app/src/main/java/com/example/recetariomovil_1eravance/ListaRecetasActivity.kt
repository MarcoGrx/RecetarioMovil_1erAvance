package com.example.recetariomovil_1eravance

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle

class ListaRecetasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas)

        val recetas = listOf(
            Receta("Receta 1", listOf("Ingrediente 1", "Ingrediente 2"), "30 minutos", "Fácil", "Vegana"),
            // Agrega más recetas aquí
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecetaAdaptador(recetas)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
