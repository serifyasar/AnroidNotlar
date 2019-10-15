package com.serifyasar.kuran_ikelimeler.Fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.serifyasar.kuran_ikelimeler.Activity.LoginActivity
import com.serifyasar.kuran_ikelimeler.Db.AyarRepository


import com.serifyasar.kuran_ikelimeler.R
import kotlinx.android.synthetic.main.fragment_profile.*

private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
private lateinit var repository: AyarRepository
class ProfileFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view= inflater.inflate(R.layout.fragment_profile, container, false)

        repository= AyarRepository(view.context)

        val profname:TextView=view.findViewById(R.id.profName)
        profname.text= repository.getUser().name

        val btn :Button= view.findViewById(R.id.buttonLogout)

        btn.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(view.context,LoginActivity::class.java))


        }



        return view
    }


}
