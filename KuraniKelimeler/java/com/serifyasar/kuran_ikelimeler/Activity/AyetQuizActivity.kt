package com.serifyasar.kuran_ikelimeler.Activity


import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.serifyasar.kuran_ikelimeler.Db.AyetRepository
import com.serifyasar.kuran_ikelimeler.Db.Ayetler
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.activity_ayet_quiz.*

class AyetQuizActivity : AppCompatActivity() {

    private var i=-1
    private var x=0
    val repository by lazy { AyetRepository(this) }
    private var aList= listOf<String>()
    private var tList= listOf<String>()
    private var idList= listOf<String>()
    private lateinit var ayetList : ArrayList<Ayetler>
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayet_quiz)


         try {

             ayetList = listeAsenkronTask(repository).execute().get() as ArrayList<Ayetler>

             setQuestion()
         }
         catch (e:Exception){
             Toast.makeText(this,"Veritabanı yüklenemedi.",Toast.LENGTH_LONG).show()
         }




    }


    private fun setQuestion(){
        i++
        tv_ayet.text= ayetList[i].arabic
        tv_meal.text= ayetList[i].turkish

        val aword=ayetList[i].aword
        val tword=ayetList[i].tword
        aList  = aword.split("-")
        tList  = tword.split("-")
        idList  = tword.split("-")
        setChip()

    }

    private fun setChip(){
        chipGroup.removeAllViews()
        selectColor()

        tList.shuffled().forEach {
            val chip = Chip(this)
            chip.textSize = 16f

            chip.text = it
            chipGroup.addView(chip)
            chip.setOnClickListener {
                if(chip.text==idList[x]) {
                     x++

                    if(x<idList.size) {
                        selectColor()
                        chip.setTextColor(Color.LTGRAY)
                    }
                    else{
                        x=0
                        setQuestion()
                    }
                }
            }
        }
    }


    private fun selectColor(){
        val ayet=ayetList[i].arabic
        val word=aList[x]
        val ind=ayet.indexOf(word)

        val mRed=ForegroundColorSpan(Color.RED)
        val mSpan=SpannableString(ayet)
        mSpan.setSpan(mRed,ind,ind+word.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv_ayet.text=mSpan

    }


    private class listeAsenkronTask(val repository: AyetRepository) : AsyncTask<Void,Void,List<Ayetler>>() {
        override fun doInBackground(vararg params: Void?): List<Ayetler>? {
            val list =repository.ayetListe()

           return list
        }

    }
}
