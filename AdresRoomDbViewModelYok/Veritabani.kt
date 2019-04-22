package com.serifyasar.adresdefteri

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Adresler::class),version = 1)
abstract class Veritabani : RoomDatabase() {

    abstract fun adresDao() : AdreslerDaoInterface

    companion object {

        @Volatile
        var INSTANCE : Veritabani? =null

        @Synchronized
        fun getInstance (context: Context) : Veritabani{

            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context.applicationContext,Veritabani::class.java,"adres_defteri")
                    .build()


            }
            return  INSTANCE as Veritabani
        }
    }

}