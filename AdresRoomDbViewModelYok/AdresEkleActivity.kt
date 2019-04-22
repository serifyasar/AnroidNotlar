package com.serifyasar.adresdefteri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_adres_ekle.*

class AdresEkleActivity : AppCompatActivity() {
  val repository by lazy { AdresRepository(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adres_ekle)




        btnEkle.setOnClickListener {
            val adres: Adresler= Adresler(name = editText.text.toString(), tel=editText3.text.toString(),adres=editText4.text.toString())
            repository.adresEkle(adres)

        Toast.makeText(this,"Kayıt eklendi",Toast.LENGTH_SHORT).show()
        }



    }
}
