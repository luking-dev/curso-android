package com.luking.ventaraf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AnuncioAdapter:ListAdapter<Anuncio,AnuncioAdapter.AnuncioViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Anuncio>(){
        override fun areItemsTheSame(oldItem: Anuncio, newItem: Anuncio): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Anuncio, newItem: Anuncio): Boolean {
            return  oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioAdapter.AnuncioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.un_anuncio, parent, false)
        return AnuncioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnuncioAdapter.AnuncioViewHolder, position: Int) {
        // obtenemos la posicion del anuncio
        val anuncio: Anuncio = getItem(position)

        // reemplazamos los valores
        holder.titulo.text = anuncio.titulo
        holder.precio.text = "$ " + anuncio.precio.toString()
    }

    // clase interna
    inner class AnuncioViewHolder(val view:View): RecyclerView.ViewHolder(view){
        val imagen = view.findViewById<ImageView>(R.id.anuncioImagen)
        val titulo = view.findViewById<TextView>(R.id.anuncioTitulo)
        val precio = view.findViewById<TextView>(R.id.anuncioPrecio)
        val boton = view.findViewById<Button>(R.id.anuncioBoton)
    }
}