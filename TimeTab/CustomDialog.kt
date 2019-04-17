package com.serifyasar.timetable

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ders_ekle.*

class CustomDialog : DialogFragment(){

    private  var rnks: renkSec?=null

    private lateinit var b1 : Button
    private lateinit var b2 : Button
    private lateinit var b3 : Button
    private lateinit var b4 : Button
    private lateinit var b5 : Button
    private lateinit var b6 : Button
    private lateinit var b7 : Button
    private lateinit var b8 : Button
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity)
        val view: View? = activity?.layoutInflater?.inflate(R.layout.renk_dialog, null)
        builder.setView(view)

        b1 = view!!.findViewById(R.id.button2)
        b2 = view.findViewById(R.id.button14)
        b3 = view.findViewById(R.id.button15)
        b4 = view.findViewById(R.id.button16)
        b5 = view.findViewById(R.id.button17)
        b6 = view.findViewById(R.id.button18)
        b7 = view.findViewById(R.id.button19)
        b8 = view.findViewById(R.id.button20)

        b1.setOnClickListener { rnks?.renk(R.color.renk1)
        this.dismiss()
        }

        b2.setOnClickListener { rnks?.renk(R.color.renk2)
            this.dismiss()
        }
        b3.setOnClickListener { rnks?.renk(R.color.renk3)
            this.dismiss()
        }
        b4.setOnClickListener { rnks?.renk(R.color.renk4)
            this.dismiss()
        }
        b5.setOnClickListener { rnks?.renk(R.color.renk5)
            this.dismiss()
        }
        b6.setOnClickListener { rnks?.renk(R.color.renk6)
            this.dismiss()
        }
        b7.setOnClickListener { rnks?.renk(R.color.renk7)
            this.dismiss()
        }
        b8.setOnClickListener { rnks?.renk(R.color.renk8)
            this.dismiss()
        }


        return builder.create()

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            rnks = context as renkSec
        }catch (e: Exception){

        }
    }

    override fun onDetach() {
        super.onDetach()
        rnks=null
    }





}