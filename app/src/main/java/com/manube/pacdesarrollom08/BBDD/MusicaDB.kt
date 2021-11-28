package com.manube.pacdesarrollom08.BBDD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [ CancionEntity::class],
    version = 1,
    exportSchema = false)

abstract class MusicaDB : RoomDatabase() {

    abstract fun musicDao():MusicaDao

    companion object{
        private var db: MusicaDB? = null
        fun getDB(context: Context): MusicaDB{
            if(db == null){
                db = Room
                    .databaseBuilder(context, MusicaDB::class.java, "cancionesdb" )
                    .build()
            }
            return db!!
        }


    }
}