package com.serifyasar.timetable

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ders_ekle.*
import kotlinx.android.synthetic.main.recycler_item.*

class DersEkleActivity() : AppCompatActivity(), renkSec {


    private lateinit var ders: Dersler
    private var renk :Int =R.color.renk1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ders_ekle)

        buttonRenk.setOnClickListener {
            val customDialog: CustomDialog = CustomDialog()
            customDialog.show(supportFragmentManager,"Renk Seç")
        }

        textView3.setOnClickListener {
            val tp=TimePicker()
            tp.show(supportFragmentManager,"saat")
        }

        textView4.setOnClickListener {
            val tp=TimePicker1()
            tp.show(this.supportFragmentManager,"saat")
        }

        val gun=intent.getIntExtra("gun",1)


        floatingActionButton.setOnClickListener {
            val saat=textView3.text.toString() + " - " + textView4.text.toString()
            ders= Dersler(name = edtDersAdi.text.toString(),teacher = edtOgretmen.text.toString(),classroom = edtSalon.text.toString(),time = saat,gun = gun,renk = renk.toString())
           val sorgu=Sorgular(this)
           val snc= sorgu.kayitEkle(ders)
            if(snc>-1) Toast.makeText(this,"Kayıt yapıldı",Toast.LENGTH_SHORT).show()
        }
    }

    override fun renk(r: Int) {
      renk=r
      buttonRenk.setBackgroundColor(ContextCompat.getColor(this,r))
    }
    override fun popupMenu(act: String, id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
