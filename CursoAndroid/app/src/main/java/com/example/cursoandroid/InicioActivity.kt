package com.example.cursoandroid

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.squareup.picasso.Picasso
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        // referencias componentes view
        val vEmail = findViewById<TextView>(R.id.mainEmail)
        val vClave = findViewById<TextView>(R.id.mainClave)

        val bundle:Bundle? = intent.extras!! // ? indica que puede no venir (ser nulo); y !!,  que sea distinto del vacio

        // datos que nos llegan de la actividad Main
        val emailMain = bundle?.getString("main_email") ?: ""
        val claveMain = bundle?.getString("main_clave") ?: ""

        // asigno valores a los componentes
        vEmail.text = emailMain
        vClave.text = claveMain

        // obtener informacion usuario desde el ws
        obtenerUsuario()
    }

    fun obtenerUsuario() {

        // instacia cliente http
        val cliente = AsyncHttpClient()

        // referencias componentes view
        val vAvatar = findViewById<ImageView>(R.id.wsAvatar)
        val vEmail = findViewById<TextView>(R.id.wsEmail)
        val vNombre = findViewById<TextView>(R.id.wsNombre)
        val vId = findViewById<TextView>(R.id.wsId)
        val vImagen = findViewById<ImageView>(R.id.imagen)

        // parametros peticion o request http
        val idAleatorio = (1..12).random()
        val url = "https://reqres.in/api/users/$idAleatorio"

        // llamada http al web service
        // (endpoint, parametro (no se pasa porque lo incluye la url), respuesta (objeto JSON))
        cliente.get(url, null, object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode:Int, headers:Array<Header>, response:JSONObject) {
                val usuario = response.getJSONObject("data")
                val email = usuario.getString("email")
                val nombre = usuario.getString("first_name")
                val apellido = usuario.getString("last_name")
                val nombreCompleto = "$nombre $apellido"
                val urlAvatar = usuario.getString("avatar")
                val id = usuario.getString("id")

                vEmail.text = email
                vNombre.text = nombreCompleto
                vId.text = id

                // obtener imagen del web service
                Picasso
                        .get()
                        .load(urlAvatar)
                        .into(vAvatar)

                // obtener imagen de internet
                Picasso
                        .get()
                        .load("https://resizer.glanacion.com/resizer/e7s5LrmahzyZg77Y2JBkQfWfFQY=/879x586/smart/filters:quality(100)/cloudfront-us-east-1.images.arcpublishing.com/lanacionar/IUJSZVP3MBC77D3RMS62PGETXU.jpg")
                        .into(vImagen)
            }
        })

    }

}