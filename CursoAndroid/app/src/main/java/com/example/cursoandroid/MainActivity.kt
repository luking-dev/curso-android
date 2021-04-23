package com.example.cursoandroid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    // TODO: Borrar SuppressLint
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // usuario registrado de fantasia
        val emailURegistrado = "juan@empresa.com"
        val claveURegistrado = "7C4A8D09CA3762AF61E59520943DC26494F8941B"

        val email = findViewById<EditText>(R.id.usuarioEmail)
        val clave = findViewById<EditText>(R.id.usuarioClave)
        val btnIngresar = findViewById<Button>(R.id.botonIngresar)
        val btnRegistro = findViewById<Button>(R.id.botonRegistro)
        val btnUsuarios = findViewById<Button>(R.id.botonUsuarios)
        /*val resultado = findViewById<TextView>(R.id.textoResultado)*/

        btnIngresar.setOnClickListener {
            val claveHash = Utilidades.HashUtils.sha1(clave.text.toString().trim())

            if (email.text.isNullOrEmpty() || clave.text.isNullOrEmpty()) {
                Toast.makeText(this@MainActivity, "Por favor complete los campos", Toast.LENGTH_LONG).show()
            }
            else if (email.text.toString().trim() == emailURegistrado && claveHash == claveURegistrado) {
                /*Toast.makeText(this@MainActivity, "Login exitoso!", Toast.LENGTH_LONG).show()*/
                abrirActividadInicio(email.text.toString(), clave.text.toString())
            }
            else {
                Toast.makeText(this@MainActivity, "La cuenta no existe", Toast.LENGTH_LONG).show()
            }

            var datosUsuario = "Email: " + email.text.toString() + " Clave: " + clave.text.toString()
            datosUsuario = Utilidades.HashUtils.sha1(clave.text.toString())
            /*resultado.text = datosUsuario*/
        }

        btnRegistro.setOnClickListener {
            abrirActividadRegistro()
        }

        btnUsuarios.setOnClickListener {
            abrirActividadUsuarios()
        }
    }

    fun abrirActividadInicio(email:String, clave:String) {
        // define un intento, con la actividad main como contexto e InicioActivity como la que inicia, como una clase del tipo java
        val intento: Intent = Intent(this@MainActivity, InicioActivity::class.java)
        // definimos valores extra que va a tener el intento, y que seran pasados a la nueva actividad (InicioActivity)
        intento.putExtra("main_email", email) // (nombre del valor destino, valor)
        intento.putExtra("main_clave", clave)
        startActivity(intento)
    }

    fun abrirActividadRegistro() {
        val intentRegistro:Intent = Intent(this@MainActivity, RegistroActivity::class.java)
        startActivity(intentRegistro)
    }

    fun abrirActividadUsuarios() {
        val intentUsuarios:Intent = Intent(this@MainActivity, UsuariosActivity::class.java)
        startActivity(intentUsuarios)
    }

}
