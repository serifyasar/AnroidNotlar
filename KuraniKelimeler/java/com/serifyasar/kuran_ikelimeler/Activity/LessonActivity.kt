package com.serifyasar.kuran_ikelimeler.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.serifyasar.kuran_ikelimeler.Adapter.CategoryViewModel
import com.serifyasar.kuran_ikelimeler.Adapter.LessonRecyAdapter
import com.serifyasar.kuran_ikelimeler.R
import com.serifyasar.kuran_ikelimeler.Util.Lesson
import com.serifyasar.kuran_ikelimeler.Util.lessonRecyclerClick
import kotlinx.android.synthetic.main.activity_lesson.*


class LessonActivity : AppCompatActivity()  {
    private val mDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var mRef: DatabaseReference
    private lateinit var lesson:Lesson
    private lateinit var lesList : ArrayList<Lesson>
    private lateinit var sharedPref: SharedPreferences
    private  var level:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        lesson=Lesson()
        lesList=ArrayList<Lesson>()
        setData()
        val layoutManager= androidx.recyclerview.widget.GridLayoutManager(this, 2)

        sharedPref = getSharedPreferences("prefQuran", Context.MODE_PRIVATE)

        recyLesson.layoutManager= layoutManager
        level=readValues()

         categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)


   }


    private fun readValues() : Int {
        val value = sharedPref.getInt("appLevel", 0)
        return value
    }


    override fun onResume() {
        super.onResume()
        readValues()
        if(level<readValues()) {
            lesList.forEach {
                it.lock = readValues()
            }

            categoryViewModel.lesson.value = Lesson("", 0)
        }
    }
    private fun setData() {

        mRef = mDatabase.reference.child("Category")


        mRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {



                for(snap in dataSnapshot.children){
                    lesson = snap.getValue(Lesson::class.java)!!

                    lesson.lock=level
                    categoryViewModel.lesson.value=lesson

                    lesList.add(categoryViewModel.lesson.value!!)

                }



            }




        })

    }




}

