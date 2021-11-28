package com.manube.pacdesarrollom08.BBDD

import androidx.room.*

@Dao
interface MusicaDao {
    //SELECT

    @Query("SELECT * FROM canciones")
     fun getCanciones(): List<CancionEntity>

    @Query("SELECT * FROM canciones WHERE nombre_Cancion = :nomCancion")
     fun getCancionNombre(nomCancion: String ): CancionEntity
    @Query("SELECT COUNT (*) FROM canciones ")
    fun getNumCanciones():Int

    //INSERT

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addCancion(album: CancionEntity)


    //DELETE

    @Query("DELETE FROM canciones WHERE nombre_Cancion = :nomCancion")
     fun borrarCancion(nomCancion: String)



}