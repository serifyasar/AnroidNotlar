package com.serifyasar.kuran_ikelimeler.Fragment


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.serifyasar.kuran_ikelimeler.Activity.RegisterActivity
import com.serifyasar.kuran_ikelimeler.Db.AyarRepository
import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.fragment_success.*
import kotlinx.android.synthetic.main.fragment_success.view.*


private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
private val mDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
private val mCurrentUser: FirebaseUser by lazy { mAuth.currentUser!! }
private lateinit var repository: AyarRepository

class SuccessFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_success, container, false)


        repository= AyarRepository(view.context)

        val lessonIndex= this.arguments!!.getInt("lessonIndex")


        if(repository.userCount()>0){
            val user= repository.getUser()
            val level= user.level
            if (lessonIndex==level) {
                setLevel(level+1)
                user.level=level+1
                repository.userUpdate(user)
            }
        }else{
            Toast.makeText(view.context,"Sonraki dersler için lütfen kayıt olunuz veya giriş yapınız..", Toast.LENGTH_LONG).show()

            Handler().postDelayed({
                startActivity(Intent(view.context, RegisterActivity::class.java))

            },1000)
        }




        view.btnBack.setOnClickListener { view ->
          activity!!.finish()
        }



        // Return the fragment view/layout
        return view
    }


    private fun setLevel(level: Int) {
        mDatabase.reference.child("users").child(mCurrentUser.uid).child("level")
            .setValue(level).addOnCompleteListener {
            if (it.isSuccessful){
                msg.text="Tebrikler... Yeni dersin kilidi açıldı.. "
            }

            }
    }


}
