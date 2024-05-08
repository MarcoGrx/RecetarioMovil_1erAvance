package com.example.recetariomovil_1eravance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class nuevaRecetaActivity : AppCompatActivity() {

    lateinit var btnVolver: Button
    lateinit var btnGuardarReceta: Button
    lateinit var btnCancelarReceta: Button
    lateinit var nombreReceta: EditText
    lateinit var ingredientesReceta: EditText
    lateinit var tiempoReceta: EditText
    lateinit var dificultadReceta: EditText
    lateinit var tipoDieta: EditText
    lateinit var procedimiento: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_receta)

        btnVolver = findViewById(R.id.btn_volver)
        btnGuardarReceta = findViewById(R.id.btn_guardar_receta)
        btnCancelarReceta = findViewById(R.id.btn_cancelar_receta)
        nombreReceta = findViewById(R.id.nombreEditText)
        ingredientesReceta = findViewById(R.id.ingredientesEditText)
        tiempoReceta = findViewById(R.id.tiempoEditText)
        dificultadReceta = findViewById(R.id.dificultadEditText)
        tipoDieta = findViewById(R.id.tipoDietaEditText)
        procedimiento = findViewById(R.id.procedimientoEditText)

        btnVolver.setOnClickListener {
            finish()
        }

        btnCancelarReceta.setOnClickListener {
            nombreReceta.setText("")
            ingredientesReceta.setText("")
            tiempoReceta.setText("")
            dificultadReceta.setText("")
            tipoDieta.setText("")
            procedimiento.setText("")
        }

    }
}