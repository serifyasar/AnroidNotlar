package com.serifyasar.fotoslayt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Photo::class),version = 1)
abstract class Veritabani  : RoomDatabase() {

    abstract fun sorgular() : SorguArayuz

    companion object {
        @Volatile
        var INSTANCE : Veritabani? =null

        @Synchronized
        fun getInstance (context: Context) : Veritabani{

            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context.applicationContext,Veritabani::class.java,"foto_db")
                    .build()
            }
            return  INSTANCE as Veritabani
        }

    }
}