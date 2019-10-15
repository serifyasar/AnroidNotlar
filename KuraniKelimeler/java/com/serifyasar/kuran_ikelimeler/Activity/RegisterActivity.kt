package com.serifyasar.kuran_ikelimeler.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.serifyasar.kuran_ikelimeler.Db.AyarRepository
import com.serifyasar.kuran_ikelimeler.Db.User
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val mDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val repository by lazy { AyarRepository(this) }
    private lateinit var mReference: DatabaseReference
    private lateinit var mUserReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            val email = edtRegEmail.text.toString().trim()
            val password = edtRegPass.text.toString().trim()
            val name = edtRegName.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                if(password.length >= 6) {
                    register_progressBar.visibility=View.VISIBLE
                    registerUser(name, email, password)
                } else {
                    edtRegPass.error = "Şifre en az 6 karakter olmalı"
                }
            } else {
                if(email.isEmpty()) edtRegEmail.error = "Email boş geçilemez"
                if(password.isEmpty()) edtRegPass. error = "Şifre boş geçilemez"
                if(name.isEmpty()) edtRegName.error = "İsim boş geçilemez"
            }

        }
    }


    private fun registerUser(name: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(!it.isSuccessful) {
                Toast.makeText(this, "Başarısız", Toast.LENGTH_SHORT).show()
                register_progressBar.visibility=View.GONE
            } else {
                val currentUser = mAuth.currentUser
                val userId = currentUser?.uid

                mReference = mDatabase.reference
                mUserReference = mReference.child("users").child(userId!!)

                val userMap = HashMap<String, String>()
                userMap["name"] = name
                userMap["level"] = "2"
                userMap["levelQ"] = "2"

                val user=User(name = name,level = 2,levelQ = 2)

                mUserReference.setValue(userMap).addOnCompleteListener {
                    Toast.makeText(this, "BAŞARILI", Toast.LENGTH_SHORT).show()

                    repository.addUser(user)

                    register_progressBar.visibility=View.GONE

                    val intent = Intent(this, SplashActivity::class.java)
                    startActivity(intent)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    finish()
                }
            }
        }

    }
}
