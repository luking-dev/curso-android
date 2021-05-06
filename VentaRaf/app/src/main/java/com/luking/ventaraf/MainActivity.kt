package com.luking.ventaraf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.luking.ventaraf.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listaAnuncios.layoutManager = LinearLayoutManager(this)
        val listaAnuncios = mutableListOf<Anuncio>()
        listaAnuncios.add(Anuncio(1, "imagen.jpg", "Titulo 1", 1000.25))
        listaAnuncios.add(Anuncio(2, "imagen.jpg", "Titulo 2", 479.50))
        listaAnuncios.add(Anuncio(3, "imagen.jpg", "Titulo 3", 10.99))
        listaAnuncios.add(Anuncio(4, "imagen.jpg", "Titulo 4", 158.00))

        // creamos una instancia de anuncioadapter
        val adapter = AnuncioAdapter()
        binding.listaAnuncios.adapter = adapter
        //adapter.submitList(listaAnuncios)

        val apiInterface = ApiInterface.create().getAnuncios()

        apiInterface.enqueue(object: Callback<List<Anuncio>> {
            // al recibir una respuesta
            override fun onResponse(call: Call<List<Anuncio>>, response: Response<List<Anuncio>>) {
                // compruebo que la respuesta tenga un cuerpo y sea distinto a nulo
                if (response?.body() != null) {
                    // si el body tiene un valor, entonces lo envio al adaptador
                    adapter.submitList(response.body())
                }
            }

            // al haber un error a la llamada de la api
            override fun onFailure(call: Call<List<Anuncio>>, t: Throwable) {

            }
        })

        // ejemplo sin recyclerview
        //val btnBuscar = findViewById<Button>(R.id.botonBuscar)
        //btnBuscar.text = "texto ejemplo"

        // ejemplo con recyclerview
        //binding.botonBuscar.text = "texto ejemplo"
    }
}