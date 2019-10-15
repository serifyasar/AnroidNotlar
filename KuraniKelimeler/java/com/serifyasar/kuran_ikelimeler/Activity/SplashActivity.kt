package com.serifyasar.kuran_ikelimeler.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.serifyasar.kuran_ikelimeler.Db.*
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var status:Long = 0
    private val mDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val mCurrentUser: FirebaseUser by lazy { mAuth.currentUser!! }
    private lateinit var mUserReference: DatabaseReference
    private lateinit var mRef: DatabaseReference
    val repository by lazy { AyarRepository(this) }
    val repo by lazy { AyetRepository(this) }
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        try {
            val user = mAuth.currentUser

            readStatus()

            Handler().postDelayed({


                if (repository.categoryCount() <= 0 || status>repository.getStatus()) {
                    repository.categorySil()
                    getCategory()
                    repo.kelimeSil()
                    getKelime()
                    val stat:Status= Status(level = status.toInt())
                    repository.statusSil()
                    repository.setStatus(stat)
                }

                if (repository.categorySureCount() <= 0 || status>repository.getStatus()) {
                    repository.categorySureSil()
                    getSureCategory()
                    repo.ayetSil()
                    getAyet()
                    val stat:Status= Status(level = status.toInt())
                    repository.statusSil()
                    repository.setStatus(stat)
                }

                val intent = Intent(this, SureActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)


            }, 3000)




        }
        catch (e: Exception){
            Toast.makeText(this,e.message.toString(),Toast.LENGTH_LONG).show()
        }


        /*
        Handler().postDelayed({
            if (user != null) {
                val intent = Intent(this, SureActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
        }, 2000)

*/

    }


    private fun getCategory() {

        mRef = mDatabase.reference.child("Category")


        mRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snap in dataSnapshot.children) {
                    val category = snap.getValue(Category::class.java)!!
                    repository.categoryEkle(category)

                }


            }


        })

    }

    private fun getSureCategory() {

        mRef = mDatabase.reference.child("CategorySure")


        mRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snap in dataSnapshot.children) {
                    val categorySure = snap.getValue(CategorySure::class.java)!!
                    repository.categorySureEkle(categorySure)

                }


            }
        })

    }

    private fun readStatus() {

        mUserReference = mDatabase.reference.child("update")

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                status= dataSnapshot.value as Long



            }

        })

    }


    private fun getAyet() {

        mRef = mDatabase.reference.child("kuran")


        mRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snap in dataSnapshot.children) {
                    val ayet = snap.getValue(Ayetler::class.java)!!
                    repo.ayetEkle(ayet)

                }


            }


        })

    }

    private fun getKelime() {

        mRef = mDatabase.reference.child("Quran")


        mRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snap in dataSnapshot.children) {
                    val kelime = snap.getValue(Kelimeler::class.java)!!
                    repo.kelimeEkle(kelime)

                }


            }


        })

    }


}




/*
    private fun saveEdtValue(s: Int,key:String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.apply {
            putInt(key, s)
            apply()
        }
    }


    private fun readValues() : Int {
        val value = sharedPref.getInt("appLevel", 0)
        return value
    }



    private fun setLevel() {
        val userId = mCurrentUser.uid
        mUserReference = mDatabase.reference.child("users").child(userId)

        mUserReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val level = dataSnapshot.child("level").value.toString()
                saveEdtValue(level.toInt(),"appLevel")

            }

        })
    }
        */

