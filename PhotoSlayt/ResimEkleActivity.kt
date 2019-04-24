package com.serifyasar.fotoslayt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_resim_ekle.*

class ResimEkleActivity : AppCompatActivity() {

    val repository by lazy { Repository(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resim_ekle)

        button.setOnClickListener {

            val rsm : Photo = Photo(resim = editText.text.toString(),detay = "Manzara Resmi")
            repository.resimEkle(rsm)
            Toast.makeText(this,"Resim eklendi",Toast.LENGTH_SHORT).show()
            editText.text.clear()


        }
    }
}
