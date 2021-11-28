package com.manube.pacdesarrollom08.Application

import android.app.Application
import com.manube.pacdesarrollom08.BBDD.MusicaDB

class App : Application(){

    companion object{
        private var db : MusicaDB? = null
        public fun getDB():MusicaDB{
            return db!!
        }

       lateinit var pref:Pref
    }



    override fun onCreate() {
        super.onCreate()

        db= MusicaDB.getDB(applicationContext)
        pref = Pref(applicationContext)


    }

}