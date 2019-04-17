package com.serifyasar.timetable

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class Sorgular(var context: Context) {

    var mDbHelper:DataBaseHelper = DataBaseHelper.getInstance(context)

    fun kayitEkle(dersler: Dersler) : Int{

        val db= mDbHelper.writableDatabase
        val veriler=ContentValues()

        veriler.apply {
            put("name",dersler.name)
            put("teacher",dersler.teacher)
            put("time",dersler.time)
            put("classroom",dersler.classroom)
            put("gun",dersler.gun)
            put("renk",dersler.renk)

        }
        val id=db.insert(DataBaseHelper.TABLE_NAME,null,veriler)
        db.close()
        return id.toInt()

    }

    fun tumKayitlar(g:Int) : ArrayList<Dersler>{
        val list = ArrayList<Dersler>()
        val db=mDbHelper.readableDatabase

        val c : Cursor = db.rawQuery("select * from ${DataBaseHelper.TABLE_NAME} where gun=$g",null)

        if (c.moveToFirst()){
            do {
                val id=c.getInt(0)
                val name=c.getString(1)
                val teacher=c.getString(2)
                val classroom=c.getString(3)
                val time1=c.getString(4)
                val gun=c.getInt(5)
                val renk=c.getString(6)

                val ders=Dersler(id,name,teacher,classroom,time1,gun,renk)
                list.add(ders)
            }while (c.moveToNext())

        }
        c.close()
        db.close()
        return  list

    }

    fun sil(id: Int) {
        val db=mDbHelper.writableDatabase
        db.delete(DataBaseHelper.TABLE_NAME,"_id = ?" , arrayOf(id.toString()))
        db.close()
    }
}