package com.example.recetariomovil_1eravance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetalleRecetaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var receta: Receta? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta)

        if(intent.hasExtra("receta")){
            receta = intent.getSerializableExtra("receta") as Receta
        }

        if (receta != null) {
            val tvNombre: TextView = findViewById(R.id.nombreTextView)
            val tvIngredientes: TextView = findViewById(R.id.ingredientesTextView)
            val tvTiempo: TextView = findViewById(R.id.tiempoTextView)
            val tvDificultad: TextView = findViewById(R.id.dificultadTextView)
            val tvTipoDieta: TextView = findViewById(R.id.tipoDietaTextView)

        }
    }
}



