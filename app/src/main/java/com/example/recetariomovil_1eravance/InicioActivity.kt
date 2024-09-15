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

class InicioActivity : AppCompatActivity() {

    lateinit var btn_entrar: Button
    lateinit var et_correo: EditText
    lateinit var et_contrasena: EditText
    lateinit var tv_olvideContrasena: TextView
    lateinit var tv_crearCuenta: TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btn_entrar = findViewById(R.id.btn_entrar)
        et_correo = findViewById(R.id.et_correo)
        et_contrasena = findViewById(R.id.et_contrasena)
        tv_olvideContrasena = findViewById(R.id.tv_olvideContrasena)
        tv_crearCuenta = findViewById(R.id.tv_crearCuenta)

        // Initialize Firebase Auth
        auth = Firebase.auth

        tv_crearCuenta.setOnClickListener {
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        tv_olvideContrasena.setOnClickListener {
            var intent = Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }

        btn_entrar.setOnClickListener {
            var email = et_correo.text.toString()
            var contraseña = et_contrasena.text.toString()

            if (email.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, contraseña)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(
                                baseContext,
                                "Bienvenido ${user?.email}",
                                Toast.LENGTH_SHORT,
                            ).show()
                            var intent = Intent(this, ListaRecetasActivity::class.java)
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed. ${task.exception?.message}",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }

        }
    }
}
