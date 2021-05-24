package com.luking.ventaraf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.luking.ventaraf.databinding.ActivityVenderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonPublicar.setOnClickListener {
            publicarAnuncio(binding)
        }
    }

    private fun publicarAnuncio(binding: ActivityVenderBinding) {
        val anuncio = Anuncio(  null,
                                binding.imagen.text.toString(),
                                binding.titulo.text.toString(),
                                binding.descripcion.text.toString(),
                                null,
                                binding.nombre.text.toString(),
                                binding.direccion.text.toString(),
                                null,
                                null,
                                binding.precio.text.toString())

        val apiInterface = ApiInterface.create().nuevoAnuncio(anuncio)

        apiInterface.enqueue(object: Callback<Anuncio> {
            // al recibir una respuesta
            override fun onResponse(call: Call<Anuncio>, response: Response<Anuncio>) {
                // compruebo que la respuesta tenga un cuerpo y sea distinto a nulo
                if (response?.body() != null) {
                    // informo al usuario que se creo el anuncio correctamente
                    Toast.makeText(this@VenderActivity, "Anuncio publicado con exito", Toast.LENGTH_LONG).show()
                }
            }

            // al haber un error a la llamada de la api
            override fun onFailure(call: Call<Anuncio>, t: Throwable) {

            }
        })
    }
}