package com.luking.ventaraf

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.luking.ventaraf.databinding.ActivityMainBinding
import com.luking.ventaraf.databinding.ActivityResultadosBuscadorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultadosBuscador : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultadosBuscadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras!!

        // terminos de busqueda
        val palabrasClave = bundle?.getString("palabras_clave") ?: ""

        // reemplazo el titulo de la activity
        binding.tituloBuscador.text = "Resultados para: $palabrasClave"

        binding.listaAnunciosBuscador.layoutManager = LinearLayoutManager(this)

        // creamos una instancia de anuncioadapter
        val adapter = AnuncioAdapter()
        binding.listaAnunciosBuscador.adapter = adapter

        val apiInterface = ApiInterface.create().getResultados(palabrasClave)

        apiInterface.enqueue(object: Callback<List<Anuncio>> {
            // al recibir una respuesta
            override fun onResponse(call: Call<List<Anuncio>>, response: Response<List<Anuncio>>) {
                // compruebo que la respuesta tenga un cuerpo y sea distinto a nulo
                if (response.body() != null) {
                    // si el body tiene un valor, entonces lo envio al adaptador
                    adapter.submitList(response.body())
                }
            }

            // al haber un error a la llamada de la api
            override fun onFailure(call: Call<List<Anuncio>>, t: Throwable) {

            }
        })
    }
}