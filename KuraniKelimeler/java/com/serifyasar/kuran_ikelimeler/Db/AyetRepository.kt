package com.serifyasar.kuran_ikelimeler.Db

import android.content.Context
import android.os.AsyncTask

class AyetRepository(context: Context) {

    private   val db by lazy { Veritabani.getInstance(context) }
    private val sorgu by lazy { db.ayetDao() }

    fun ayetListe() : List<Ayetler> = sorgu.ayetListe()
    fun kelimeListe(k_id:Int) : List<Kelimeler> =sorgu.kelimeListe(k_id)
    fun categoryListe() : List<Category> = sorgu.categoryListe()
    fun categorySureListe() : List<CategorySure> = sorgu.categorySureListe()


    fun ayetEkle( ayet:Ayetler) = insertAsenkronTask(sorgu).execute(ayet)
    fun kelimeEkle( kelime: Kelimeler) = insertKelimeAsenkronTask(sorgu).execute(kelime)
    fun ayetSil() = deleteAsenkronTask(sorgu).execute()
    fun kelimeSil() = deleteKelimeAsenkronTask(sorgu).execute()





    private class insertAsenkronTask(val sorgu : DaoInterface): AsyncTask<Ayetler,Void,Void>(){
        override fun doInBackground(vararg params: Ayetler?): Void? {

            sorgu.ayetEkle(params[0]!!)
            return null
        }

    }

    private class insertKelimeAsenkronTask(val sorgu : DaoInterface): AsyncTask<Kelimeler,Void,Void>(){
        override fun doInBackground(vararg params: Kelimeler?): Void? {

            sorgu.kelimeEkle(params[0]!!)
            return null
        }

    }

    private class deleteAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {

            sorgu.ayetleriSil()
            return null
        }

    }
    private class deleteKelimeAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {

            sorgu.kelimeleriSil()
            return null
        }

    }


}