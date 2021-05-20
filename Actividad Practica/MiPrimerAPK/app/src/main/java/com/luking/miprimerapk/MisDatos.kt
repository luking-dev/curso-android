package com.luking.miprimerapk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MisDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_datos)

        // referencias componentes view
        val vEmail = findViewById<TextView>(R.id.email)
        val vClaveEncriptada = findViewById<TextView>(R.id.claveEncriptada)

        // defino un conjunto de datos a recibir desde el intento
        val bundle: Bundle? = intent.extras!!

        // datos desde la actividad mainactivity para un futuro uso si se llegara a requerir
        val emailMain = bundle?.getString("main_email") ?: ""
        val claveEncriptadaMain = bundle?.getString("main_clave_encriptada") ?: ""

        // asigno valores a los componentes
        vEmail.text = emailMain
        vClaveEncriptada.text = claveEncriptadaMain
    }
}