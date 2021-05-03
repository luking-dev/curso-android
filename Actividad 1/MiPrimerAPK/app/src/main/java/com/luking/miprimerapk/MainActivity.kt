package com.luking.miprimerapk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // referencias componentes view
        val vEmail = findViewById<EditText>(R.id.email)
        val vClave = findViewById<EditText>(R.id.clave)
        val btnIngresar = findViewById<Button>(R.id.botonIngresar)
        val btnNuevoUsuario = findViewById<Button>(R.id.botonNuevoUsuario)

        // seteo foco al primer campo
        vEmail.requestFocus()

        // accion al dar click al boton ingresar
        btnIngresar.setOnClickListener {
            // encriptamos la clave para compararla con que podriamos tener almacenada en una bdd
            val claveEncriptada = Registro.HashUtils.sha1(vClave.text.toString())

            if (vEmail.text.toString().isNullOrEmpty() || vClave.text.toString().isNullOrEmpty()) {
                // aviso al usuario que hay campos incompletos
                Toast.makeText(this, "Complete todos los datos para loguearse", Toast.LENGTH_LONG).show()
            } else {
                // inicio la actividad misdatos
                abrirActividadMisDatos(vEmail.text.toString(), claveEncriptada)
            }
        }

        // accion al dar click al boton nuevo usuario
        btnNuevoUsuario.setOnClickListener {
            // inicio la actividad registro
            abrirActividadRegistro()
        }
    }

    fun abrirActividadMisDatos(email: String, claveEncriptada: String) {
        // defino intento
        val intento: Intent = Intent(this@MainActivity, MisDatos::class.java)

        // defino valores extra del intento para pasar como parametros a la actividad misdatos
        intento.putExtra("main_email", email)
        intento.putExtra("main_clave_encriptada", claveEncriptada)

        // inicio la actividad
        startActivity(intento)
    }

    fun abrirActividadRegistro() {
        // defino intento
        val intento: Intent = Intent(this@MainActivity, Registro::class.java)

        // inicio la actividad
        startActivity(intento)
    }
}