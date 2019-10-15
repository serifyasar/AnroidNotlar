package com.serifyasar.kuran_ikelimeler.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import com.serifyasar.kuran_ikelimeler.R
import com.serifyasar.kuran_ikelimeler.Util.Lesson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mDatabase : FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private lateinit var mRef: DatabaseReference
    private  val lesson : ArrayList<Lesson> by lazy { ArrayList<Lesson>()  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

button2t.setOnClickListener {
    startActivity(Intent(this,AyetQuizActivity::class.java))

}

        buttonyt.setOnClickListener {
            startActivity(Intent(this,LessonActivity::class.java))

        }

    }


}

