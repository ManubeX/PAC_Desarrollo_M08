package com.manube.pacdesarrollom08


import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.manube.pacdesarrollom08.Application.App
import com.manube.pacdesarrollom08.Application.Pref
import com.manube.pacdesarrollom08.BBDD.CancionEntity
import com.manube.pacdesarrollom08.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var music: MediaPlayer? = null
    lateinit var vista: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vista = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vista.root)

        music = MediaPlayer.create(this, R.raw.music)

        //Comprueba si la aplicación ya se había iniciado, y en caso de que no se haya iniciado nunca carga datos en la BBDD
        if(!App.pref.getInit()){
            cargarDatos()

        }
        controlesMusica(vista)
        botones(vista)


    }


    /**
     * Se encarga de controlar la música y otorgar a cada botón una función
     */
    private fun controlesMusica (vista:ActivityMainBinding){

        vista.play.setOnClickListener{
            if(!music?.isPlaying!!) {
                music!!.start()

            }
        }

        vista.pause.setOnClickListener{
            if(music?.isPlaying!!){
                music!!.pause()

            }
        }

        vista.stop.setOnClickListener{
           music?.stop()
            music = MediaPlayer.create(this, R.raw.music)
        }


    }


    /**
     * Da funcionalidad a los botones para pasar de activitys
     */
    fun botones (vista:ActivityMainBinding){

        vista.btGaleria.setOnClickListener{
            val intent = Intent(this, GaleriaActivity::class.java)
            startActivity(intent)
        }

        vista.btBBDD.setOnClickListener{
            val intent = Intent(this, BBDDActivity::class.java)
            startActivity(intent)
        }

        vista.btCamara.setOnClickListener{
            val intent = Intent(this, CamaraActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Para la música al pausar la activity
     */
    override fun onPause() {
        super.onPause()
        music?.release()
        music = null

        println("Pausado")
    }

    override fun onResume() {
        super.onResume()
        music = MediaPlayer.create(this, R.raw.music)
    }

    /**
     * Carga datos en la BBDD
     */
    fun cargarDatos(){

        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Animals", album_Cancion = "Animals", anno = 2013, artista_Cancion = "Martin Garrix"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Happy", album_Cancion = "Mad World", anno = 2020, artista_Cancion = "Timmy Trumpet"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "When Love Takes", album_Cancion = "One Love", anno = 2009, artista_Cancion = "David Guetta"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Dangerous", album_Cancion = "Listen", anno = 2014, artista_Cancion = "David Guetta"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Unity", album_Cancion = "Tomorrowland 2018 EP", anno = 2018, artista_Cancion = "Dimitri Vegas & Like Mike"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Hey Baby", album_Cancion = "Hey Baby", anno = 2013, artista_Cancion = "Dimitri Vegas & Like Mike"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "In the Name of Love", album_Cancion = "In the Name of Love", anno = 2016, artista_Cancion = "Martin Garrix"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Last Dance", album_Cancion = "Last Dance", anno = 2012, artista_Cancion = "Avicii"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Wake Me Up", album_Cancion = " True", anno = 2013, artista_Cancion = "Avicii"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Limit Break", album_Cancion = "Next Generation EP", anno = 2015, artista_Cancion = "NLW"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Ten Feet Tall", album_Cancion = "Forget the World", anno = 2014, artista_Cancion = " Afrojack"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Earthquakey People", album_Cancion = "Wonderland", anno = 2012, artista_Cancion = "Steve Aoki"))
                App.getDB().musicDao().addCancion(CancionEntity(nombre_Cancion = "Steve Aoki Neon Future III", album_Cancion = "Steve Aoki Neon Future III", anno = 2018, artista_Cancion = "Steve Aoki"))
            }

        }

        App.pref.saveIni(true)
    }



}

