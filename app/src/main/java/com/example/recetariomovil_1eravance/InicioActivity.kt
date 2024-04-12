package com.example.recetariomovil_1eravance

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {

    lateinit var btn_entrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btn_entrar = findViewById(R.id.btn_entrar)

        btn_entrar.setOnClickListener {
            intent.setClass(this, ListaRecetasActivity::class.java)
            startActivity(intent)
        }

        // Aquí puedes configurar tus elementos de UI si es necesario
    }
}
