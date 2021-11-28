package com.manube.pacdesarrollom08.menusBBDD

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.manube.pacdesarrollom08.Application.App
import com.manube.pacdesarrollom08.BBDD.CancionEntity
import com.manube.pacdesarrollom08.BBDDActivity
import com.manube.pacdesarrollom08.R
import com.manube.pacdesarrollom08.databinding.ActivityBuscarCancionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BuscarCancion : AppCompatActivity() {

    lateinit var vista : ActivityBuscarCancionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vista = ActivityBuscarCancionBinding.inflate(layoutInflater)
        setContentView(vista.root)

        botones()
    }

    /**
     * Da funcionalidad a los botones
     */
    private fun botones(){

        //Busca el texto introducido en el campo textView en la BBDD
        vista.botonBuscar.setOnClickListener {


            //Hace la busqueda en la BBDD
            lifecycleScope.launch {
                val nombre :String = vista.campoBuscar.text.toString()

                val cancion : CancionEntity? = withContext(Dispatchers.IO) {

                    App.getDB().musicDao().getCancionNombre(nombre)
                }


                vista.campoMusica.isVisible = true  //Muestra una tarjeta con el resultado
                if (cancion != null) {
                    //si se obtiene un resultado de la BBDD rellena los textView con los datos
                    vista.tituloCancion.text = cancion.nombre_Cancion
                    vista.autorCancion.text = cancion.artista_Cancion
                    vista.albumCancion.text = cancion.album_Cancion
                    vista.annoCancion.text = cancion.anno.toString()
                    vista.borrar.isVisible=true


                }else {
                    //si no encuentra el título de la canción muestra una tarjeta preestablecida
                    vista.tituloCancion.text = "Canción no encontrada"
                    vista.autorCancion.text = ""
                    vista.albumCancion.text = ""
                    vista.annoCancion.text = ""
                    vista.borrar.isVisible=false
                }

            }

        }


        /**
         * activa el boton borrar y permite borrar una canción
         */
        vista.borrar.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle(R.string.dialog_title)
                setMessage(R.string.dialog_message)
                setPositiveButton(R.string.dialog_yes){_: DialogInterface, _: Int ->

                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            App.getDB().musicDao().borrarCancion(vista.tituloCancion.text.toString())

                        }
                    }

                    toast()
                    vista.campoMusica.isVisible = false
                    vista.borrar.isVisible=false

                }
                setNegativeButton(R.string.dialog_no, null)
            }.show()
        }

        vista.volver.setOnClickListener {
            val intent = Intent(this, BBDDActivity::class.java)
            startActivity(intent)
        }
    }

    fun toast(){
        val toast = Toast.makeText(this, R.string.cancion_borrada, Toast.LENGTH_LONG).show()
    }





}