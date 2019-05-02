package com.serifyasar.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.serifyasar.quiz.R
import kotlinx.android.synthetic.main.activity_sonuc.*

class SonucActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sonuc)

        val d=intent.getIntExtra("dogru",0)
        val y=intent.getIntExtra("yanlis",0)

        tvSonuc.text="Doğru= ${d} - Yanlış =${y}"

    }
}
