package com.example.recetariomovil_1eravance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ListaRecetasActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var btnNuevaReceta: Button
    lateinit var btnFavoritos: Button
    lateinit var et_buscador: EditText
    lateinit var btnBuscar: Button

    private var allRecetas: MutableList<Receta> = mutableListOf()
    private var recetas: MutableList<Receta> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas)

        btnNuevaReceta = findViewById(R.id.btn_nueva_receta)
        btnFavoritos = findViewById(R.id.btn_favoritos)
        et_buscador = findViewById(R.id.et_buscador)
        btnBuscar = findViewById(R.id.btn_buscar)

        btnBuscar.setOnClickListener(){
        val buscarTexto= et_buscador.text.toString()
            recetas.clear()
            recetas.addAll(allRecetas.filter { it.nombre.contains(buscarTexto,ignoreCase = true) })
            viewAdapter.notifyDataSetChanged()
        }

        btnNuevaReceta.setOnClickListener {
            val intent = Intent(this, nuevaRecetaActivity::class.java)
            startActivity(intent)
        }

        val receta1 = Receta(
            nombre = "Ensalada César",
            ingredientes = listOf(
                "Lechuga romana",
                "Pan",
                "Pechuga de pollo",
                "Queso parmesano",
                "Salsa César",
                "Aceite de oliva",
                "Sal y pimienta al gusto"
            ),
            tiempoPreparacion = "20 minutos",
            dificultad = "Fácil",
            tipoDieta = "Vegetariana",
            procedimientos = listOf(
                "Lava y seca bien las hojas de lechuga romana, corta las hojas en trozos medianos y colócalas en un tazón grande para ensaladas.",
                "Corta el pan en rebanadas finas o en cubos pequeños, rocía con aceite de oliva, sal y pimienta, luego tuesta en sartén u horno hasta que esté dorado y crujiente.",
                "Añade los crutones (pan tostado) encima de la ensalada.",
                "Rocía la salsa César sobre la ensalada según tu gusto, mezcla suavemente todos los ingredientes para distribuir el aliño de manera uniforme.",
                "Sirve la Ensalada César en platos individuales y disfruta."
            ),
            imagen = R.drawable.ensalada_cesar
        )

        val receta2 = Receta(
            nombre = "Sopa de Tomate",
            ingredientes = listOf("Tomates maduros", "Cebolla", "Ajo", "Caldo de verduras", "Albahaca fresca"),
            tiempoPreparacion = "30 minutos",
            dificultad = "Fácil",
            tipoDieta = "Vegetariana",
            procedimientos = listOf(
                "Lava y corta los tomates en trozos.",
                "Pica finamente la cebolla y el ajo.",
                "En una olla grande, calienta un poco de aceite de oliva a fuego medio.",
                "Agrega la cebolla y el ajo picados y sofríe hasta que estén dorados.",
                "Añade los tomates cortados a la olla y cocina hasta que estén blandos.",
                "Agrega el caldo de verduras a la olla y deja que hierva.",
                "Reduce el fuego y deja cocinar a fuego lento durante unos 20 minutos.",
                "Retira la olla del fuego y deja que la sopa se enfríe un poco.",
                "Usando una licuadora de mano o una licuadora de pie, licua la sopa hasta que quede suave.",
                "Vuelve a colocar la sopa en la olla y caliéntala a fuego medio.",
                "Agrega hojas de albahaca fresca picada y sal al gusto.",
                "Sirve la sopa caliente y disfruta."
            ),
            imagen = R.drawable.sopa_tomate
        )

        val receta3 = Receta(
            nombre = "Lasaña de Carne",
            ingredientes = listOf("Carne molida", "Salsa de tomate", "Queso ricotta", "Queso mozzarella", "Pasta para lasaña"),
            tiempoPreparacion = "1 hora",
            dificultad = "Intermedia",
            tipoDieta = "Omnívora",
            procedimientos = listOf(
                "Precalienta el horno a 180°C (350°F).",
                "En una sartén grande, cocina la carne molida a fuego medio hasta que esté bien cocida.",
                "Agrega la salsa de tomate a la carne molida y cocina por unos minutos más.",
                "En un recipiente aparte, mezcla el queso ricotta con sal y pimienta al gusto.",
                "En un molde para hornear, coloca una capa delgada de salsa de tomate en el fondo.",
                "Coloca una capa de láminas de pasta para lasaña sobre la salsa de tomate en el molde.",
                "Agrega una capa de carne molida sobre la pasta para lasaña.",
                "Coloca cucharadas de la mezcla de queso ricotta sobre la carne molida.",
                "Espolvorea con queso mozzarella rallado.",
                "Repite las capas de pasta para lasaña, carne molida, queso ricotta y queso mozzarella hasta que llenes el molde o se te acaben los ingredientes, terminando con una capa de queso mozzarella en la parte superior.",
                "Cubre el molde con papel de aluminio y hornea en el horno precalentado durante unos 30-40 minutos.",
                "Retira el papel de aluminio y hornea por otros 10-15 minutos, o hasta que el queso esté dorado y burbujeante.",
                "Retira del horno y deja reposar durante unos minutos antes de cortar en porciones y servir."
            ),
            imagen = R.drawable.lasana_carne
        )

        val receta4 = Receta(
            nombre = "Salmón al Horno",
            ingredientes = listOf("Filete de salmón", "Limón", "Ajo", "Aceite de oliva", "Espinacas frescas"),
            tiempoPreparacion = "25 minutos",
            dificultad = "Intermedia",
            tipoDieta = "Pescetariana",
            procedimientos = listOf(
                "Precalienta el horno a 200°C (390°F).",
                "Coloca el filete de salmón en una bandeja para hornear forrada con papel de aluminio.",
                "Exprime el jugo de limón sobre el salmón.",
                "Pica finamente el ajo y esparce sobre el salmón.",
                "Rocía el aceite de oliva sobre el salmón y espolvorea con sal y pimienta al gusto.",
                "Hornea el salmón en el horno precalentado durante aproximadamente 15-20 minutos, o hasta que esté cocido y se desmenuce fácilmente con un tenedor.",
                "Mientras se hornea el salmón, lava las espinacas frescas y escúrrelas.",
                "En una sartén grande, saltea las espinacas con un poco de aceite de oliva y ajo picado hasta que estén marchitas y tiernas.",
                "Sirve el salmón caliente con las espinacas salteadas y acompaña con rodajas de limón."
            ),
            imagen = R.drawable.salmon_horno
        )

        val receta5 = Receta(
            nombre = "Tacos Veganos",
            ingredientes = listOf("Tortillas de maíz", "Frijoles negros", "Aguacate", "Salsa de tomate", "Lechuga"),
            tiempoPreparacion = "15 minutos",
            dificultad = "Fácil",
            tipoDieta = "Vegana",
            procedimientos = listOf(
                "Calienta las tortillas de maíz en una sartén o en el microondas hasta que estén suaves y calientes.",
                "Calienta los frijoles negros en una cacerola pequeña a fuego medio-bajo.",
                "Pela y corta el aguacate en rodajas finas.",
                "Lava y corta la lechuga en tiras finas.",
                "Para armar los tacos, coloca una porción de frijoles negros calientes en el centro de cada tortilla.",
                "Añade algunas rodajas de aguacate encima de los frijoles.",
                "Agrega una cucharada de salsa de tomate sobre el aguacate.",
                "Espolvorea un poco de lechuga picada sobre la salsa.",
                "Dobla las tortillas por la mitad para formar los tacos y sirve de inmediato."
            ),
            imagen = R.drawable.tacos_veganos
        )

        allRecetas = listOf(receta1, receta2, receta3, receta4, receta5).toMutableList()
        recetas= ArrayList(allRecetas)

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecetaAdaptador(recetas)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        (viewAdapter as RecetaAdaptador).setOnClickListener(object : RecetaAdaptador.OnClickListener {
            override fun onClick(position: Int, model: Receta) {
                val intent = Intent(this@ListaRecetasActivity, DetalleRecetaActivity::class.java)
                intent.putExtra("receta", model)
                startActivity(intent)
            }

        })
    }

}
