package com.luking.ventaraf


data class Anuncio(
    val id: Int?,
    val imagen: String,
    val titulo: String,
    val descripcion: String,
    val fecha: String?,
    val nombre: String,
    val direccion: String,
    val email: String?,
    val celular: String?,
    var precio: String
) {

}