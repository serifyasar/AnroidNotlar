package com.serifyasar.quiz.data

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.serifyasar.quiz.api.ApiClient
import com.serifyasar.quiz.api.ApiQuestion
import com.serifyasar.quiz.api.Sorular
import retrofit2.Call
import retrofit2.Response

class Repository(context: Context) {

    private  val db by lazy { Veritabani.getInstance(context) }
    private val sorgu by lazy { db.sorguDao() }


    fun tumSorular() : LiveData<List<Question>> = sorgu.tumSorular()
    fun soruEkle(question: Question) = soruEkleTask(sorgu).execute(question)
   fun soruAdet() : Int = sorgu.soruAdet()

    private class soruEkleTask(val sorguArayuz: SorguDao) : AsyncTask<Question,Void,Void>(){
        override fun doInBackground(vararg params: Question?): Void? {

            sorguArayuz.soruEkle(params[0]!!)
            return null

        }
    }



}