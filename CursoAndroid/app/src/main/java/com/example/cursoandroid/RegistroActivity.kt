package com.example.cursoandroid

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi

class RegistroActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnTomarFoto = findViewById<Button>(R.id.botonTomarFoto)
        val btnRegistrarme = findViewById<Button>(R.id.botonRegistro)

        val vNombre = findViewById<EditText>(R.id.nombreCompleto)
        val vEmail = findViewById<EditText>(R.id.email)
        val vClave = findViewById<EditText>(R.id.clave)
        val vConfirmarClave = findViewById<EditText>(R.id.repetirClave)
        // el avatar no se va a utilizar por el momento
        // val vAvatar = findViewById<Button>(R.id.avatar)

        btnTomarFoto.setOnClickListener() {
            intentoTomarFoto()
        }

        btnRegistrarme.setOnClickListener() {
            if (vNombre.text.isNotEmpty() && vEmail.text.isNotEmpty() && vClave.text.isNotEmpty()) {
                // comprobamos que ambas claves son identicas (previo al hasheo)
                if (vClave.text.toString() == vConfirmarClave.text.toString()) {
                    // encriptamos la clave
                    val claveHash = Utilidades.HashUtils.sha1(vClave.text.toString())

                    // guardamos al usuario a la base de datos
                    guardarUsuarioEnBD(vNombre.text.toString(), vEmail.text.toString(), claveHash, "")
                } else {
                    Utilidades.notificacion(this, "Verifique sus datos. Las claves no coinciden")
                }
            } else {
                Utilidades.notificacion(this, "Todos los campos son requeridos")
            }
        }

    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun intentoTomarFoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val vVistaPrevia = findViewById<ImageView>(R.id.vistaPrevia)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            vVistaPrevia.setImageBitmap(imageBitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun guardarUsuarioEnBD(nombre: String, email: String, clave: String, avatar: String) {
        // base de datos
        val usuarios = BaseDeDatos(this)
        val db = usuarios.writableDatabase

        // contenedor de valores para cada registro
        val nuevoUsuario = ContentValues()
        nuevoUsuario.put("nombre", nombre)
        nuevoUsuario.put("email", email)
        nuevoUsuario.put("clave", clave)
        nuevoUsuario.put("avatar", avatar)

        // insertamos los valores en la base de datos
        db.insert("usuarios", null, nuevoUsuario)
        db.close()

        // mostramos una notificacion al usuario en caso de exito y lo enviamos a MainActivity
        abrirActividadMain("Logueese con sus datos")
    }

    fun abrirActividadMain(notificacion: String) {
        // define un intento, con la actividad main como contexto e MainActivity como la que inicia, como una clase del tipo java
        val intento: Intent = Intent(this@RegistroActivity, MainActivity::class.java)

        // definimos valores extra que va a tener el intento, y que seran pasados a la nueva actividad (MainActivity)
        intento.putExtra("registro_notificacion", notificacion)
        startActivity(intento)
    }

}