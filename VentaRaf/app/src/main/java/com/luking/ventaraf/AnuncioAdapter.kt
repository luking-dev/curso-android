package com.luking.ventaraf

import android.annotation.SuppressLint
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
import com.squareup.picasso.Picasso

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AnuncioAdapter.AnuncioViewHolder, position: Int) {
        // obtenemos la posicion del anuncio
        val anuncio: Anuncio = getItem(position)

        // reemplazamos los valores
        holder.titulo.text = anuncio.titulo
        val precio = anuncio.precio
        holder.precio.text = "$$precio"
        holder.boton.setOnClickListener {
            val intentDetalle = Intent(holder.boton.context, DetalleAnuncio::class.java)
            intentDetalle.putExtra("id_anuncio", anuncio.id.toString())
            intentDetalle.putExtra("latitud", anuncio.latitud.toString())
            intentDetalle.putExtra("longitud", anuncio.longitud.toString())
            intentDetalle.putExtra("email", anuncio.email.toString())
            intentDetalle.putExtra("celular", anuncio.celular.toString())
            
            holder.boton.context.startActivity(intentDetalle)
        }

        // dibujo imagen en el view con picasso
        Picasso
            .get()
            .load(anuncio.imagen) // imagen anuncio resumen
            .into(holder.imagen) // imageview del layout
    }

    // clase interna
    inner class AnuncioViewHolder(view:View): RecyclerView.ViewHolder(view){
        val imagen = view.findViewById<ImageView>(R.id.anuncioImagen)
        val titulo = view.findViewById<TextView>(R.id.anuncioTitulo)
        val precio = view.findViewById<TextView>(R.id.anuncioPrecio)
        val boton = view.findViewById<Button>(R.id.anuncioBoton)
    }
}