package com.serifyasar.kuran_ikelimeler.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val p: Int= intent.getIntExtra("id",0)+1
        val name: String= intent.getStringExtra("name")



        tvDetailName.text=name
        tvPosition.text="Konu " + p.toString()

        btnPractise.setOnClickListener {
            val intent=Intent(this,PractiseActivity::class.java)
            intent.putExtra("id",p)
            intent.putExtra("name",name)

            startActivity(intent)
        }

        btnTest.setOnClickListener {
            val intent=Intent(this,QuizActivity::class.java)
            intent.putExtra("id",p)
            intent.putExtra("name",name)

            startActivity(intent)
        }

        btnQuranTest.setOnClickListener {
            val intent=Intent(this,QuranQuizActivity::class.java)
            intent.putExtra("id",p)
            intent.putExtra("name",name)

            startActivity(intent)
        }

    }
}
