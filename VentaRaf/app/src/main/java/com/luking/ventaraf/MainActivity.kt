package com.luking.ventaraf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.luking.ventaraf.databinding.ActivityMainBinding

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
        adapter.submitList(listaAnuncios)

        // ejemplo sin recyclerview
        //val btnBuscar = findViewById<Button>(R.id.botonBuscar)
        //btnBuscar.text = "texto ejemplo"

        // ejemplo con recyclerview
        //binding.botonBuscar.text = "texto ejemplo"
    }
}