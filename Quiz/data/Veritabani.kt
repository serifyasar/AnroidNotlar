package com.serifyasar.quiz.data

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.serifyasar.quiz.api.ApiClient
import com.serifyasar.quiz.api.ApiQuestion
import retrofit2.Call
import retrofit2.Response


@Database(entities = arrayOf(Question::class),version = 1)
abstract class Veritabani() : RoomDatabase() {

    abstract fun sorguDao(): SorguDao

    companion object {
        @Volatile
        var INSTANCE: Veritabani? = null

        @Synchronized
        fun getInstance(context: Context): Veritabani {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, Veritabani::class.java, "db01")
                .addCallback(roomDbCallback)
                .build()

            }
            return INSTANCE as Veritabani

        }

        private val roomDbCallback = object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                AsenkronSoruEkle(INSTANCE).execute()
            }
        }



        class AsenkronSoruEkle(private val db: Veritabani?): AsyncTask<Void, Void, Void>() {

             val dao: SorguDao? by lazy { db?.sorguDao() }

            override fun doInBackground(vararg params: Void?): Void? {
                val apiClient  by lazy { ApiClient.apiCagir() }
                apiClient.soruCek(50,"multiple").enqueue(object : retrofit2.Callback<ApiQuestion>{
                    override fun onFailure(call: Call<ApiQuestion>, t: Throwable) {
                        Log.d("Hata",t.message.toString())

                    }

                    override fun onResponse(call: Call<ApiQuestion>, response: Response<ApiQuestion>) {

                        for (item in response.body()!!.sorular){
                            try {

                                val soru: Question = Question(soru = item.question,cevap_a = item.answer,cevap_b =item.other[0],cevap_c = item.other[1],cevap_d = item.other[2],cevap = item.answer)

                                soruEkleTask(dao).execute(soru)

                            }
                            catch (e:Exception){
                                Log.d("Dikkat", e.toString())
                            }

                        }

                    }

                })



                return null
            }



            private class soruEkleTask(val sorguArayuz: SorguDao?) : AsyncTask<Question,Void,Void>(){
                override fun doInBackground(vararg params: Question?): Void? {

                    sorguArayuz?.soruEkle(params[0]!!)
                    return null

                }
            }

        }





        }





}
