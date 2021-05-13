package com.luking.ventaraf

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

        apiInterface.enqueue(object: Callback<List<AnuncioResumen>> {
            // al recibir una respuesta
            override fun onResponse(call: Call<List<AnuncioResumen>>, response: Response<List<AnuncioResumen>>) {
                // compruebo que la respuesta tenga un cuerpo y sea distinto a nulo
                if (response?.body() != null) {
                    // si el body tiene un valor, entonces lo envio al adaptador
                    adapter.submitList(response.body())
                }
            }

            // al haber un error a la llamada de la api
            override fun onFailure(call: Call<List<AnuncioResumen>>, t: Throwable) {

            }
        })
    }

    fun abrirResultadosBuscador(palabrasClave: String) {
        val intento: Intent = Intent(this@ResultadosBuscador, ResultadosBuscador::class.java)
        intento.putExtra("palabras_clave", palabrasClave)
        startActivity(intento)
    }
}