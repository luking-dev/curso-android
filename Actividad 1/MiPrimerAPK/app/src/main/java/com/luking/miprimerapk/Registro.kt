package com.luking.miprimerapk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.security.MessageDigest

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // referencias componentes view
        val nombre = findViewById<EditText>(R.id.nombre)
        val apellido = findViewById<EditText>(R.id.apellido)
        val email = findViewById<EditText>(R.id.email)
        val clave = findViewById<EditText>(R.id.clave)
        val btnRegistrarme = findViewById<Button>(R.id.botonRegistrarme)

        // seteo foco al primer campo
        nombre.requestFocus()

        // concateno el nombre y apellido para obtener el nombre completo
        val nombreCompleto = "$nombre $apellido"

        // accion al dar click al boton registrarme
        btnRegistrarme.setOnClickListener() {
            // compruebo que todos los campos esten completos
            if (nombre.text.isNotEmpty() && apellido.text.isNotEmpty() && email.text.isNotEmpty() && clave.text.isNotEmpty()) {
                // compruebo que se haya completado el campo clave
                if (clave.text.isNotEmpty()) {
                    // encripto la clave
                    val claveHash = HashUtils.sha1(clave.text.toString())

                    // aviso al usuario que el registro se completo exitosamente
                    Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_LONG).show()

                    // inicio la actividad MisDatos
                    abrirActividadMainActivity("Logueese con sus datos")
                } else {
                    // aviso al usuario que se ingresaron claves distintas
                    Toast.makeText(this, "Verifique sus datos. Las claves no coinciden", Toast.LENGTH_LONG).show()
                }
            } else {
                // aviso al usuario que hay campos incompletos
                Toast.makeText(this, "Todos los campos son requeridos", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun abrirActividadMainActivity(notificacion: String) {
        // defino intento
        val intento: Intent = Intent(this@Registro, MainActivity::class.java)

        // defino valores extra del intento para pasar como parametros a la actividad mainactivity
        intento.putExtra("registro_notificacion", notificacion)

        // inicio la actividad
        startActivity(intento)
    }

    object HashUtils {
        fun sha256(input: String) = hashString("SHA-256", input)

        fun sha1(input: String) = hashString("SHA-1", input)

        private fun hashString(type: String, input: String): String {
            val HEX_CHARS = "0123456789ABCDEF"
            val bytes = MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
            val result = StringBuilder(bytes.size * 2)

            bytes.forEach {
                val i = it.toInt()
                result.append(HEX_CHARS[i shr 4 and 0x0f])
                result.append(HEX_CHARS[i and 0x0f])
            }

            return result.toString()
        }
    }
}