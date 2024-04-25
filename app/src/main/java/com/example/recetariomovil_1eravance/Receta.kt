package com.example.recetariomovil_1eravance

data class Receta(val nombre: String,
                  val ingredientes: List<String>,
                  val tiempoPreparacion: String,
                  val dificultad: String,
                  val tipoDieta: String,
                  val imagen: Int? = null,
                  val procedimientos: String? = null):java.io.Serializable

