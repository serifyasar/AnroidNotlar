package com.serifyasar.kuran_ikelimeler.Activity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.serifyasar.kuran_ikelimeler.Db.AyetRepository

import com.serifyasar.kuran_ikelimeler.Db.Kelimeler
import com.serifyasar.kuran_ikelimeler.R

import kotlinx.android.synthetic.main.activity_practise.*

class PractiseActivity : AppCompatActivity() {


    private var x=0
    private var i=-1
    private var total=0

    private var wordList: ArrayList<Kelimeler> = arrayListOf()
    val repository by lazy { AyetRepository(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practise)

        val p: Int= intent.getIntExtra("id",0)

        val name: String= intent.getStringExtra("name")






        txtName.text="Konu : "+ name
        wordList= listeAsenkronTask(repository).execute(p).get() as ArrayList<Kelimeler>

        total=wordList.size
        x=100/total
        txtTotal.text="Kelime Sayısı : " + total

            btnNext.setOnClickListener {

            if(i<total-1) {
                i++
                newWord(i)
                progressBar2.progress+=x

            }

            if(i==(total-1))
                progressBar2.progress=100

        }

        btnPrevious.setOnClickListener {

            if(i>0) {
                i--
                newWord(i)
                progressBar2.progress-=x
            }
        }

        btnStart.setOnClickListener {
            if(i<total-1) {
                i++
                newWord(i)
                progressBar2.progress+=x

            }
            btnStart.visibility=View.GONE
            txtTotal.visibility=View.GONE
            txtName.visibility=View.GONE
            panel1.visibility=View.VISIBLE
            panel2.visibility=View.VISIBLE
        }
    }


    private fun newWord(s:Int){


        txtKuran.text=wordList[s].kuran.toString()
        txtMeal.text=wordList[s].meal.toString()
        txtArabic.text=wordList[s].arabic.toString()
        txtTurkish.text=wordList[s].turkish.toString()



    }

    private class listeAsenkronTask(val repository: AyetRepository) : AsyncTask<Int, Void, List<Kelimeler>>() {
        override fun doInBackground(vararg params: Int?): List<Kelimeler>? {
            val list = params[0]?.let { repository.kelimeListe(it) }

            return list
        }

    }

}
