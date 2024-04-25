package com.example.recetariomovil_1eravance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RecuperacionActivity : AppCompatActivity() {
    lateinit var btn_recuperar: Button
    lateinit var et_correo: EditText
    lateinit var btn_volver: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)

        btn_recuperar = findViewById(R.id.btn_recuperar)
        et_correo = findViewById(R.id.et_correo)
        btn_volver = findViewById(R.id.btn_volver)

        // Initialize Firebase Auth
        auth = Firebase.auth

        btn_recuperar.setOnClickListener {
            val email = et_correo.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Se ha enviado un correo para recuperar la contraseña.",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "No se ha podido enviar el correo.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        btn_volver.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }
    }
}