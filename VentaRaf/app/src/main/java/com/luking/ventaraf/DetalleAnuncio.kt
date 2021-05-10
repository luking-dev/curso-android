package com.luking.ventaraf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}