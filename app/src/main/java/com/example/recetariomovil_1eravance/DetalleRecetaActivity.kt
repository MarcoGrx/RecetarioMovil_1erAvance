package com.example.recetariomovil_1eravance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetalleRecetaActivity : AppCompatActivity() {

    lateinit var btnVolver: Button
    lateinit var tvNombre: TextView
    lateinit var tvIngredientes: TextView
    lateinit var tvTiempo: TextView
    lateinit var tvDificultad: TextView
    lateinit var tvTipoDieta: TextView
    lateinit var tvProcedimiento: TextView
    lateinit var imagenReceta: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta)

        var receta: Receta? = null
        btnVolver = findViewById(R.id.btn_volver)
        tvNombre = findViewById(R.id.nombreTextView)
        tvIngredientes = findViewById(R.id.ingredientesTextView)
        tvTiempo = findViewById(R.id.tiempoTextView)
        tvDificultad = findViewById(R.id.dificultadTextView)
        tvTipoDieta = findViewById(R.id.tipoDietaTextView)
        tvProcedimiento = findViewById(R.id.procedimientoTextView)
        imagenReceta = findViewById(R.id.imagenReceta)

        btnVolver.setOnClickListener {
            finish()
        }

        if(intent.hasExtra("receta")){
            receta = intent.getSerializableExtra("receta") as Receta
        }

        if (receta != null) {
            tvNombre.text = receta.nombre
            tvIngredientes.text = receta.ingredientes
            tvTiempo.text = receta.tiempoPreparacion
            tvDificultad.text = receta.dificultad
            tvTipoDieta.text = receta.tipoDieta

            val procedimientosFormateados = formatProcedimientos(receta.procedimientos)
            tvProcedimiento.text = procedimientosFormateados

            Picasso.get().load(receta.imagen).into(imagenReceta)
        }
    }

    fun formatProcedimientos(procedimientos: String): String {
        val pasos = procedimientos.split(".")
        val builder = StringBuilder()

        pasos.forEachIndexed { index, paso ->
            val pasoNumerado = "${index + 1}) $paso\n\n"
            builder.append(pasoNumerado)
        }

        return builder.toString()
    }
}



