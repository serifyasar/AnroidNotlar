package com.serifyasar.kuran_ikelimeler.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Ayetler::class,User::class,Category::class,CategorySure::class,Status::class,Kelimeler::class),version = 1)
abstract class Veritabani : RoomDatabase() {

    abstract fun ayetDao() : DaoInterface

    companion object {

        @Volatile
        var INSTANCE : Veritabani? =null

        @Synchronized
        fun getInstance (context: Context) : Veritabani{

            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context.applicationContext,Veritabani::class.java,"quran")
                    .build()


            }
            return  INSTANCE as Veritabani
        }
    }

}