package com.manube.pacdesarrollom08.BBDD

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity (tableName = "canciones")

data class CancionEntity (
    @PrimaryKey (autoGenerate = true) val id_cancion: Int =0,
    val nombre_Cancion: String,
    val anno: Int?,
    val artista_Cancion: String,
    val album_Cancion: String

        ) {
    override fun toString(): String {
        return "CancionEntity(nombre_Cancion='$nombre_Cancion', numPista=$anno, artista_Cancion='$artista_Cancion', album_Cancion='$album_Cancion')"
    }
}
