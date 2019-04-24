package com.serifyasar.fotoslayt

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class Repository(context: Context) {

    private  val db by lazy { Veritabani.getInstance(context) }
    private val sorgu by lazy { db.sorgular() }


    fun tumResimler() : LiveData<List<Photo>> = sorgu.tumResimler()
    fun resimEkle(photo: Photo) = resimEkleTask(sorgu).execute(photo)

    private class resimEkleTask(val sorguArayuz: SorguArayuz) : AsyncTask<Photo,Void,Void>(){
        override fun doInBackground(vararg params: Photo?): Void? {

            sorguArayuz.resimEkle(params[0]!!)
            return null

        }


    }

}