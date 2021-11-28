package com.manube.pacdesarrollom08.RecyclerView


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manube.pacdesarrollom08.BBDD.CancionEntity
import com.manube.pacdesarrollom08.databinding.MusicaCardBinding

/**
 * Adapter para el recyclerView de la lista de canciones
 */


class CancionesAdapter (val canciones:List<CancionEntity>): RecyclerView.Adapter<CancionesAdapter.CancionHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionHolder {
        val itemBinding = MusicaCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CancionHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CancionHolder, position: Int) {
        holder.render(canciones[position])
    }

    override fun getItemCount(): Int = canciones.size


    class CancionHolder(val itemBinding: MusicaCardBinding ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun render(cancion: CancionEntity) {
            itemBinding.tituloCancion.text = cancion.nombre_Cancion
            itemBinding.albumCancion.text = cancion.album_Cancion
            itemBinding.annoCancion.text = cancion.anno.toString()
            itemBinding.autorCancion.text = cancion.artista_Cancion

        }

    }
}