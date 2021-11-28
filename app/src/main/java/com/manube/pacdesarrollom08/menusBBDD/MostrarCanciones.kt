package com.manube.pacdesarrollom08.menusBBDD

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.manube.pacdesarrollom08.Application.App
import com.manube.pacdesarrollom08.BBDD.CancionEntity
import com.manube.pacdesarrollom08.BBDDActivity
import com.manube.pacdesarrollom08.RecyclerView.CancionesAdapter
import com.manube.pacdesarrollom08.databinding.ActivityMostrarCancionesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MostrarCanciones : AppCompatActivity() {
    lateinit var vista: ActivityMostrarCancionesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vista = ActivityMostrarCancionesBinding.inflate(layoutInflater)
        setContentView(vista.root)


        //Crea una List de los registros de la BBDD y los pasa a la funcion del RecyclerView
        lifecycleScope.launch {
            val lista : List<CancionEntity> = withContext(Dispatchers.IO){
                App.getDB().musicDao().getCanciones()
            }
            initRecycler(lista)
        }

        vista.buttonVolverMostrar.setOnClickListener{
            val intent = Intent(this, BBDDActivity::class.java)
            startActivity(intent)
        }
    }


    /**
     * Gestiona el RecyclerView
     */
    fun initRecycler(listaMusica : List<CancionEntity> ){
        vista.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CancionesAdapter(listaMusica)
        vista.recyclerView.adapter = adapter
    }

}