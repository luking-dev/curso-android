package com.luking.ventaraf

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.luking.ventaraf.databinding.ActivityDetalleAnuncioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalleAnuncio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetalleAnuncioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras!!

        val idAnuncio = bundle?.getString("id_anuncio") ?: ""

        // accion para el boton llamarahora
        binding.botonLlamarAhora.setOnClickListener {
            llamarAhora(binding.celular.text.toString())
        }

        // llamada a nuestra api para recuperar un anuncio completo
        val apiInterface = ApiInterface.create().getAnuncio(idAnuncio.toInt())

        apiInterface.enqueue(object: Callback<Anuncio> {
            override fun onResponse(call: Call<Anuncio>, response: Response<Anuncio>) {
                // compruebo que la respuesta tenga un cuerpo y sea distinto a nulo
                if (response.body() != null) {
                    // si el body tiene un valor, entonces lo envio al layout
                    response.body()!!.precio = "$" + response.body()!!.precio
                    binding.anuncio = response.body()
                }
            }

            override fun onFailure(call: Call<Anuncio>, t: Throwable) {
            }

        })
    }

    fun llamarAhora(celular: String) {
        // establece el formato como "tel:" seguido del numero de telefono
        val uri = Uri.parse("tel:$celular")

        // crea el intento y establece los datos para el numero de telefono
        val intentoLlamarAhora: Intent = Intent(Intent.ACTION_DIAL, uri)

        startActivity(intentoLlamarAhora)
    }
}