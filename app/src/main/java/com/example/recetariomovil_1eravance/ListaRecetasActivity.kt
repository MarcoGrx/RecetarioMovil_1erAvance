package com.example.recetariomovil_1eravance

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ListaRecetasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecetaAdaptador
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var btnNuevaReceta: Button
    lateinit var btnFavoritos: Button
    lateinit var et_buscador: EditText
    lateinit var btnBuscar: Button

    var img: Int = 0
    private val db = FirebaseFirestore.getInstance()
    private val coleccion = db.collection("recetas")
    private lateinit var adaptador: RecetaAdaptador


    private var allRecetas: MutableList<Receta> = mutableListOf()
    private var recetas: MutableList<Receta> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas)

        btnNuevaReceta = findViewById(R.id.btn_nueva_receta)
        btnFavoritos = findViewById(R.id.btn_favoritos)
        et_buscador = findViewById(R.id.et_buscador)
        btnBuscar = findViewById(R.id.btn_buscar)
        recyclerView = findViewById(R.id.recyclerView)

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecetaAdaptador(recetas)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            recyclerView.adapter = adapter

        }

        (viewAdapter as RecetaAdaptador).setOnClickListener(object :
            RecetaAdaptador.OnClickListener {
            override fun onClick(position: Int, model: Receta) {
                val intent = Intent(this@ListaRecetasActivity, DetalleRecetaActivity::class.java)
                intent.putExtra("receta", model)
                startActivity(intent)
            }

        })

        consultar()

        btnBuscar.setOnClickListener() {
            val buscarTexto = et_buscador.text.toString()
            recetas.clear()
            recetas.addAll(allRecetas.filter { it.nombre.contains(buscarTexto, ignoreCase = true) })
            viewAdapter.notifyDataSetChanged()
        }

        btnNuevaReceta.setOnClickListener {
            val intent = Intent(this, nuevaRecetaActivity::class.java)
            startActivity(intent)
        }

    }

    private fun consultar(){
        coleccion.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val nombre = document.getString("Nombre").toString()
                    val ingredientes = document.getString("Ingredientes").toString()
                    val tiempoPreparacion = document.getString("Tiempo").toString()
                    val dificultad = document.getString("Dificultad").toString()
                    val tipoDieta = document.getString("TipoDieta").toString()
                    val procedimientos = document.getString("Procedimiento").toString()
                    val imagen = document.getString("Imagen").toString()

                    var plato = Receta(
                        nombre = nombre,
                        ingredientes = ingredientes,
                        tiempoPreparacion = tiempoPreparacion,
                        dificultad = dificultad,
                        tipoDieta = tipoDieta,
                        procedimientos = procedimientos,
                        imagen = imagen
                    )

                    allRecetas.add(plato)
                }

                // Llenar recetas con todos los datos al principio
                recetas.addAll(allRecetas)

                // Notificar al adaptador que los datos han cambiado
                viewAdapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}