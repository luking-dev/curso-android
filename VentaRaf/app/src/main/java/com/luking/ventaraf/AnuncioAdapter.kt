package com.luking.ventaraf

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AnuncioAdapter:ListAdapter<AnuncioResumen,AnuncioAdapter.AnuncioViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<AnuncioResumen>(){
        override fun areItemsTheSame(oldItem: AnuncioResumen, newItem: AnuncioResumen): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnuncioResumen, newItem: AnuncioResumen): Boolean {
            return  oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnuncioAdapter.AnuncioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.un_anuncio, parent, false)
        return AnuncioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnuncioAdapter.AnuncioViewHolder, position: Int) {
        // obtenemos la posicion del anuncio
        val anuncio: AnuncioResumen = getItem(position)

        // reemplazamos los valores
        holder.titulo.text = anuncio.titulo
        holder.precio.text = "$ " + anuncio.precio.toString()
        holder.boton.setOnClickListener {
            val intentDetalle: Intent = Intent(holder.boton.context, DetalleAnuncio::class.java)
            intentDetalle.putExtra("id_anuncio", anuncio.id.toString())
            holder.boton.context.startActivity(intentDetalle)
        }
    }

    // clase interna
    inner class AnuncioViewHolder(val view:View): RecyclerView.ViewHolder(view){
        val imagen = view.findViewById<ImageView>(R.id.anuncioImagen)
        val titulo = view.findViewById<TextView>(R.id.anuncioTitulo)
        val precio = view.findViewById<TextView>(R.id.anuncioPrecio)
        val boton = view.findViewById<Button>(R.id.anuncioBoton)
    }
}