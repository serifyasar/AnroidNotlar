package com.serifyasar.timetable

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), renkSec {


    private lateinit var sorgular: Sorgular
    private var g: Int = 1
    private lateinit var adapter: RecyclerAdapter
    private lateinit var dersListe : ArrayList<Dersler>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                dersListe=sorgular.tumKayitlar(tab!!.position?.plus(1))
                adapter.updateList(dersListe)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        sorgular= Sorgular(this)




        dersListe=sorgular.tumKayitlar(g)
        adapter=RecyclerAdapter(dersListe)
        recyclerView.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerView.adapter=adapter



        fabButon.setOnClickListener {

        val intent=Intent(this,DersEkleActivity::class.java)
            intent.putExtra("gun",tabLayout.selectedTabPosition+1)
            startActivity(intent)

        }
    }


    override fun onResume() {
        super.onResume()
        dersListe=sorgular.tumKayitlar(tabLayout.selectedTabPosition+1)
        adapter.updateList(dersListe)

    }
    override fun renk(r: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popupMenu(act: String, id: Int) {
      if(act.equals("Sil")){
          sorgular.sil(id)
          dersListe=sorgular.tumKayitlar(tabLayout.selectedTabPosition+1)
          adapter.updateList(dersListe)
      }


    }

}
