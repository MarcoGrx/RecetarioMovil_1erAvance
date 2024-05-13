package com.example.recetariomovil_1eravance

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException
import java.util.*

class nuevaRecetaActivity : AppCompatActivity() {

    companion object {
        private const val CODIGO_SELECCION_IMAGEN = 101
    }

    lateinit var btnVolver: Button
    lateinit var btnGuardarReceta: Button
    lateinit var btnCancelarReceta: Button
    lateinit var nombreReceta: EditText
    lateinit var ingredientesReceta: EditText
    lateinit var tiempoReceta: EditText
    lateinit var dificultadReceta: EditText
    lateinit var tipoDieta: EditText
    lateinit var procedimiento: EditText
    lateinit var imagenReceta: ImageView
    lateinit var btnSubirImagen: Button
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStorage: FirebaseStorage? = null

    lateinit var nombre: String
    lateinit var ingredientes: String
    lateinit var tiempo: String
    lateinit var dificultad: String
    lateinit var dieta: String
    lateinit var proced: String
    lateinit var map: HashMap<String,String>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_receta)

        db = FirebaseFirestore.getInstance()
        map = HashMap<String,String>() // Inicializa el objeto map aquí

        btnVolver = findViewById(R.id.btn_volver)
        btnGuardarReceta = findViewById(R.id.btn_guardar_receta)
        btnCancelarReceta = findViewById(R.id.btn_cancelar_receta)
        nombreReceta = findViewById(R.id.nombreEditText)
        ingredientesReceta = findViewById(R.id.ingredientesEditText)
        tiempoReceta = findViewById(R.id.tiempoEditText)
        dificultadReceta = findViewById(R.id.dificultadEditText)
        tipoDieta = findViewById(R.id.tipoDietaEditText)
        procedimiento = findViewById(R.id.procedimientoEditText)
        imagenReceta = findViewById(R.id.imagenReceta)
        btnSubirImagen= findViewById(R.id.btn_subir_imagen)

        firebaseStorage = FirebaseStorage.getInstance()

        btnSubirImagen.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST)
        }

        imagenReceta.setOnClickListener {
            seleccionarImagen()
        }

        btnGuardarReceta.setOnClickListener {
            nombre = nombreReceta.text.toString()
            ingredientes = ingredientesReceta.text.toString()
            tiempo = tiempoReceta.text.toString()
            dificultad = dificultadReceta.text.toString()
            dieta = tipoDieta.text.toString()
            proced = procedimiento.text.toString()

            if (nombre.isEmpty() && ingredientes.isEmpty() && tiempo.isEmpty() &&
                dificultad.isEmpty() && dieta.isEmpty() && proced.isEmpty()){
                Toast.makeText(applicationContext, "Llene todos los campos por favor.", Toast.LENGTH_SHORT).show()
            }else{
                uploadImage() // Sube la imagen a Firebase Storage
            }
        }

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

    private fun seleccionarImagen() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, CODIGO_SELECCION_IMAGEN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null )
        {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imagenReceta.setImageBitmap(bitmap)
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {

        if(filePath != null) {
            val ref = firebaseStorage?.reference?.child("images/" + UUID.randomUUID().toString())
            ref?.putFile(filePath!!)
                ?.addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        map.put("Imagen", uri.toString())
                        guardarReceta()
                    }
                }
                ?.addOnFailureListener { e ->
                    Toast.makeText(applicationContext, "Fallo al subir imagen " + e.message, Toast.LENGTH_LONG).show()
                }
        }else{
            Toast.makeText(applicationContext, "Por favor sube una imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarReceta() {

        map.put("Nombre",nombre)
        map.put("Ingredientes",ingredientes)
        map.put("Tiempo",tiempo)
        map.put("Dificultad",dificultad)
        map.put("TipoDieta",dieta)
        map.put("Procedimiento",proced)

        db.collection("recetas").add(map).addOnSuccessListener {
            Toast.makeText(applicationContext, "Se guardo con exito la receta", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "No se logro guardar la receta", Toast.LENGTH_SHORT).show()
        }
    }
}