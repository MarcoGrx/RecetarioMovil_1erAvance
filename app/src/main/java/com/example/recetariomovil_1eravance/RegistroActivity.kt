package com.example.recetariomovil_1eravance

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RegistroActivity : AppCompatActivity() {
    lateinit var btn_registrar : Button
    lateinit var et_nombre : EditText
    lateinit var et_correo : EditText
    lateinit var et_contrasena : EditText
    lateinit var et_confirmar_contrasena : EditText
    lateinit var tv_yaTengoCuenta : TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btn_registrar = findViewById(R.id.btn_registrar)
        et_nombre = findViewById(R.id.et_nombre)
        et_correo = findViewById(R.id.et_correo)
        et_contrasena = findViewById(R.id.et_contrasena)
        et_confirmar_contrasena = findViewById(R.id.et_confirmar_contrasena)
        tv_yaTengoCuenta = findViewById(R.id.tv_yaTienesCuenta)


        // Initialize Firebase Auth
        auth = Firebase.auth

        database = Firebase.database.reference

        btn_registrar.setOnClickListener {
            var nombre = et_nombre.text.toString()
            var email = et_correo.text.toString()
            var contrasena = et_contrasena.text.toString()
            var confirmarContrasena = et_confirmar_contrasena.text.toString()

            if (email.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show()
            } else if (contrasena != confirmarContrasena) {
                Toast.makeText(this, "Las contrasenas no coinciden.", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, contrasena)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(baseContext, "Usuario creado con exito ${user?.email}", Toast.LENGTH_SHORT).show()
                            finish()
                            // ...
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "No se pudo registrar el usuario",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }
            }
        }

        tv_yaTengoCuenta.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }
    }

}