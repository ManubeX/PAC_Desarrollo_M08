package com.manube.pacdesarrollom08.Application

import android.content.Context

class Pref(val context: Context) {
    val SHARED_NAME= "iniBBDD"
    val SHARED_INIT = "init"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveIni(init : Boolean){
        storage.edit().putBoolean(SHARED_INIT,init).apply()

    }

    fun getInit():Boolean{
        return storage.getBoolean(SHARED_INIT, false)
    }
}