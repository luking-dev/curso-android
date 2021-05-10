package com.luking.ventaraf

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


data class Anuncio(val id: Int,
                   val imagen: String,
                   val titulo: String,
                   val descripcion: String,
                   val fecha: String,
                   val nombre: String,
                   val direccion: String,
                   val email: String,
                   val celular: String,
                   var precio: String) {

}