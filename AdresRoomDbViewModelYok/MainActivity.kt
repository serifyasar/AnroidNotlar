package com.serifyasar.adresdefteri

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.serifyasar.adresdefteri.R.id.tvList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val sorgu by lazy { AdresRepository(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





       listeAsenkronTask(sorgu).execute(tvList)




    }


    private class listeAsenkronTask(val repository: AdresRepository) : AsyncTask<TextView,Void,Void>() {
        override fun doInBackground(vararg params: TextView?): Void? {
            val list =repository.adresListesi()

            for(item in list){
                params[0]?.text=  params[0]?.text.toString().plus("\n").plus(item.name)
            }

            return null
                            }

    }
}
