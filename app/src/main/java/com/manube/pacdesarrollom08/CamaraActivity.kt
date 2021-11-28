package com.manube.pacdesarrollom08


import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.manube.pacdesarrollom08.databinding.ActivityCamaraBinding


class CamaraActivity : AppCompatActivity() {
    lateinit var vista: ActivityCamaraBinding
    val PERMISION_CAMERA_CODE = 1
    val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vista= ActivityCamaraBinding.inflate(layoutInflater)

        setContentView(vista.root)
        botones()


    }

    /**
     * Da funciones a los botones
     */
    private fun botones(){
        vista.capturar.setOnClickListener {
            checkPermissions()
        }

        vista.botonVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //Petición de permisos
    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            //Permiso denegado por el momento
            requestCameraPermission()
        }else{
            //abrir cámara
            openCamera()
        }
    }


    private fun requestCameraPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //Mensaje para que vaya a ajustes
            AlertDialog.Builder(this).apply {
                setTitle(R.string.titulo_permiso_alert)
                setMessage(R.string.men_permiso_ajuste)
                setPositiveButton(R.string.dialog_yes){ _: DialogInterface, _: Int ->
                    startActivity(Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS))
                }
                setNegativeButton(R.string.dialog_no, null)
            }.show()
        }else{
            //Pedir permisos
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),PERMISION_CAMERA_CODE)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode== PERMISION_CAMERA_CODE){
            //permiso aceptado cámara
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera()

            }else{
                val toast = Toast.makeText(this,R.string.titulo_permiso_alert,Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Función que activa la cámara
     */
    private fun openCamera() {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    /**
     * Función que muestra la imagen capturada en una ImageView
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            vista.imagenCamara.setImageBitmap(imageBitmap)
        }
    }


}