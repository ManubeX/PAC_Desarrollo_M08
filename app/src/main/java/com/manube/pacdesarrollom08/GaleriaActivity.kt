package com.manube.pacdesarrollom08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manube.pacdesarrollom08.databinding.ActivityGaleriaBinding

class GaleriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vista = ActivityGaleriaBinding.inflate(layoutInflater)
        setContentView(vista.root)

        vista.volverDeGaleria.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


}