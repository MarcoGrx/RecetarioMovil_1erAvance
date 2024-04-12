package com.example.recetariomovil_1eravance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle

class ListaRecetasActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas)

        val receta1 = Receta(
            nombre = "Ensalada César",
            ingredientes = listOf("Lechuga romana", "Crutones", "Queso parmesano", "Pollo a la parrilla", "Aderezo César"),
            tiempoPreparacion = "20 minutos",
            dificultad = "Fácil",
            tipoDieta = "Baja en calorías"
        )

        val receta2 = Receta(
            nombre = "Sopa de Tomate",
            ingredientes = listOf("Tomates maduros", "Cebolla", "Ajo", "Caldo de verduras", "Albahaca fresca"),
            tiempoPreparacion = "30 minutos",
            dificultad = "Fácil",
            tipoDieta = "Vegetariana"
        )

        val receta3 = Receta(
            nombre = "Lasaña de Carne",
            ingredientes = listOf("Carne molida", "Salsa de tomate", "Queso ricotta", "Queso mozzarella", "Pasta para lasaña"),
            tiempoPreparacion = "1 hora",
            dificultad = "Intermedia",
            tipoDieta = "Omnívora"
        )

        val receta4 = Receta(
            nombre = "Salmón al Horno",
            ingredientes = listOf("Filete de salmón", "Limón", "Ajo", "Aceite de oliva", "Espinacas frescas"),
            tiempoPreparacion = "25 minutos",
            dificultad = "Intermedia",
            tipoDieta = "Pescetariana"
        )

        val receta5 = Receta(
            nombre = "Tacos Veganos",
            ingredientes = listOf("Tortillas de maíz", "Frijoles negros", "Aguacate", "Salsa de tomate", "Lechuga"),
            tiempoPreparacion = "15 minutos",
            dificultad = "Fácil",
            tipoDieta = "Vegana"
        )

        val recetas = listOf(receta1, receta2, receta3, receta4, receta5)

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecetaAdaptador(recetas)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        (viewAdapter as RecetaAdaptador).setOnClickListener(object : RecetaAdaptador.OnClickListener {
            override fun onClick(position: Int, model: Receta) {
                val intent = Intent(this@ListaRecetasActivity, DetalleRecetaActivity::class.java)
                intent.putExtra("receta", model)
                startActivity(intent)
            }

        })
    }

}
