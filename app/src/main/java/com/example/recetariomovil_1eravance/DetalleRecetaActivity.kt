package com.example.recetariomovil_1eravance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetalleRecetaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_receta)

        val receta = intent.getParcelableExtra<Receta>("receta")

        val nombreTextView = findViewById<TextView>(R.id.nombreTextView)
        nombreTextView.text = receta?.nombre

        // Aquí puedes mostrar los demás detalles de la receta
    }
}

