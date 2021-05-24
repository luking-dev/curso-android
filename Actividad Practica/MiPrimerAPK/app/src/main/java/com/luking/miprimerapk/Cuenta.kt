package com.luking.miprimerapk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Cuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)

        // referencias componentes view
        val vNombre = findViewById<TextView>(R.id.nombre)
        val vApellido = findViewById<TextView>(R.id.apellido)
        val vEmail = findViewById<TextView>(R.id.email)
        val vClave = findViewById<TextView>(R.id.clave)

        // defino un conjunto de datos a recibir desde el intento
        val bundle: Bundle? = intent.extras!!

        // recupero datos enviados desde la actividad registro
        val nombre = bundle?.getString("nombre")
        val apellido = bundle?.getString("apellido")
        val email = bundle?.getString("email")
        val claveEncriptada = bundle?.getString("clave_encriptada")

        // reemplazo los textview con los valores recibidos de mainactivity o registro
        // si recibe de mainactivity: nombre = "Lucas" y apellido = "Zurverra"
        // si recibe de registro:     nombre y apellido van a ser los que se usaron para registrar
        vNombre.text = nombre
        vApellido.text = apellido
        vEmail.text = email
        vClave.text = claveEncriptada
    }
}