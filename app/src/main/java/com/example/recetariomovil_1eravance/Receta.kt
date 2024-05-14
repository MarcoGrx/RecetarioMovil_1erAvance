package com.example.recetariomovil_1eravance

data class Receta(val nombre: String,
                  val ingredientes: String,
                  val tiempoPreparacion: String,
                  val dificultad: String,
                  val tipoDieta: String,
                  val imagen: String,
                  val procedimientos: String):java.io.Serializable

