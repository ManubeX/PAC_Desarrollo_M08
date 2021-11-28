package com.manube.pacdesarrollom08




import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.manube.pacdesarrollom08.databinding.ActivityBbddBinding
import com.manube.pacdesarrollom08.menusBBDD.AddCancion
import com.manube.pacdesarrollom08.menusBBDD.BuscarCancion
import com.manube.pacdesarrollom08.menusBBDD.MostrarCanciones


class BBDDActivity : AppCompatActivity() {
    lateinit var vista : ActivityBbddBinding

    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        vista = ActivityBbddBinding.inflate(layoutInflater)
        setContentView(vista.root)

        val toast = Toast.makeText(this,R.string.activity_BBDD, Toast.LENGTH_LONG).show()

        botones()


    }

    /**
     * Da funcionalidad a los botones
     */

    fun botones (  ){


        vista.btAddCanciones.setOnClickListener {
            val intent = Intent(this, AddCancion::class.java)
            startActivity(intent)
        }

        vista.btCancion.setOnClickListener {
            val intent = Intent(this, MostrarCanciones::class.java)
            startActivity(intent)
        }

        vista.buttonVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        vista.btbusqueda.setOnClickListener {
            val intent = Intent(this, BuscarCancion::class.java)
            startActivity(intent)
        }

    }


}
