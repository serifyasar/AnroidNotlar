package com.serifyasar.adresdefteri

import android.content.Context
import android.os.AsyncTask

class AdresRepository(context: Context) {

 private   val db by lazy { Veritabani.getInstance(context) }
    private val sorgu by lazy { db.adresDao() }

    fun adresListesi() : List<Adresler> = sorgu.adresListesi()
    fun adresEkle( adres: Adresler) = insertAsenkronTask(sorgu).execute(adres)



    private class insertAsenkronTask(val sorgu : AdreslerDaoInterface): AsyncTask<Adresler,Void,Void>(){
        override fun doInBackground(vararg params: Adresler?): Void? {

            sorgu.adresEkle(params[0]!!)
            return null
        }

    }



}