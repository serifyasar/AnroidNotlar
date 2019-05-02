package com.serifyasar.quiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import com.serifyasar.quiz.R
import com.serifyasar.quiz.api.ApiClient
import com.serifyasar.quiz.api.ApiQuestion
import com.serifyasar.quiz.data.Question
import com.serifyasar.quiz.data.Repository
import com.serifyasar.quiz.data.SorguDao
import com.serifyasar.quiz.data.Veritabani
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: SoruViewModel
    private lateinit var sorular : MutableList<Question>
    private var s=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


      //  ilkSoruAl()
        viewModel=ViewModelProviders.of(this).get(SoruViewModel::class.java)

        viewModel.sorular.observe(this, Observer {
            if(it.isNotEmpty()) {
                sorular= it as MutableList<Question>

                soruCek()

                btnNext.setOnClickListener {
                    val secilen=findViewById<Chip>(chipGrup.checkedChipId)
                    if(secilen.text==sorular[s].cevap)
                        viewModel.d++
                    else
                        viewModel.y++

                    viewModel.index++
                    if (viewModel.index<10) {
                        sorular.removeAt(s)
                        soruCek()

                    }else{

                        val intent:Intent = Intent(this,SonucActivity::class.java)
                        intent.putExtra("dogru",viewModel.d)
                        intent.putExtra("yanlis",viewModel.y)

                        viewModel.d=0
                        viewModel.y=0
                        viewModel.index=0

                        chipGrup.clearCheck()
                        startActivity(intent)

                    }


                }
            }
        })


    }



    fun soruCek(){

        s=Random().nextInt(sorular.size-1)
        var liste= listOf<String>(sorular[s].cevap_a,sorular[s].cevap_b, sorular[s].cevap_c,sorular[s].cevap_d)
        liste= liste.shuffled()
        chipGrup.clearCheck()
        tvSoru.text=sorular[s].soru
        chipA.text=liste.get(0)
        chipB.text=liste.get(1)
        chipC.text=liste.get(2)
        chipD.text=liste.get(3)

    }


}
