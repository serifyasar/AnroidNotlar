package com.serifyasar.timetable

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper (context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME="timetable"
        private val DATABASE_VERSION=4
        val TABLE_NAME="dersler"

        private var mInstance: DataBaseHelper? = null

        @Synchronized fun getInstance(context: Context): DataBaseHelper {
            if(mInstance == null) {
                mInstance = DataBaseHelper(context.applicationContext)
            }

            return mInstance as DataBaseHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME(_id integer primary key autoincrement, name text, teacher text, classroom text, time text,gun integer,renk text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }


}