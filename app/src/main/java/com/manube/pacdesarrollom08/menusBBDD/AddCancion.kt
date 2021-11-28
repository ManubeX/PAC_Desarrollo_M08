package com.manube.pacdesarrollom08.menusBBDD

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.manube.pacdesarrollom08.Application.App
import com.manube.pacdesarrollom08.BBDD.CancionEntity
import com.manube.pacdesarrollom08.BBDDActivity
import com.manube.pacdesarrollom08.R
import com.manube.pacdesarrollom08.databinding.ActivityAddCancionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddCancion : AppCompatActivity() {

    lateinit var vista : ActivityAddCancionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vista = ActivityAddCancionBinding.inflate(layoutInflater)
        setContentView(vista.root)

        vista.btAceptar.setOnClickListener {

            var nombre = vista.etNombreCancion.text.toString()
            var album = vista.etNombreAlbum.text.toString()
            var annio = vista.etAnno.text.toString().toInt()
            var artista = vista.etArtista.text.toString()

            var cancion = CancionEntity(
                nombre_Cancion = nombre,
                album_Cancion = album,
                anno = annio,
                artista_Cancion = artista
            )

            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    App.getDB().musicDao().addCancion(cancion)
                }

            }

            val toast = Toast.makeText(this, R.string.cancion_add, Toast.LENGTH_LONG).show()

            val intent = Intent(this, BBDDActivity::class.java)
            startActivity(intent)

        }

        vista.btCancelar.setOnClickListener{
            val toast = Toast.makeText(this, R.string.cancelado, Toast.LENGTH_LONG).show()
            val intent = Intent(this, BBDDActivity::class.java)
            startActivity(intent)

        }
    }


}