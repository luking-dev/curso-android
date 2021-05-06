package com.luking.ventaraf

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


data class Anuncio(val id: Int, val imagen: String, val titulo: String, val precio: Double) {

}