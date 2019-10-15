package com.serifyasar.kuran_ikelimeler.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.serifyasar.kuran_ikelimeler.Db.AyarRepository
import com.serifyasar.kuran_ikelimeler.Db.User
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val mDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val repository by lazy { AyarRepository(this) }
    private lateinit var mCurrentUser: FirebaseUser
    private lateinit var mUserReference: DatabaseReference
    private var dbUser:User = User(name = "no",levelQ = 1,level = 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {



            val email = edtEmailLog.text.toString()
            val password = edtPassLog.text.toString()
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                       mCurrentUser = mAuth.currentUser!!
                        readUserData()

                        startActivity(Intent(this,SplashActivity::class.java))



                    } else {

                        Toast.makeText(baseContext, "Hatalı giriş.",
                            Toast.LENGTH_SHORT).show()

                    }


                }



        }

        txtRegister.setOnClickListener {

            startActivity(Intent(this,RegisterActivity::class.java))

}
    }



    private fun readUserData() {

       mUserReference= mDatabase.reference.child("users").child(mCurrentUser.uid)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val name = dataSnapshot.child("name").value.toString()
                val level = dataSnapshot.child("level").value.toString().toInt()
                val levelQ = dataSnapshot.child("levelQ").value.toString().toInt()

                dbUser=User(name = name,level = level,levelQ = levelQ)
                repository.userDelete()
                repository.addUser(dbUser)

            }

        })

    }

}
