package com.serifyasar.kuran_ikelimeler.Fragment


import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.serifyasar.kuran_ikelimeler.Activity.AyetQuizActivity
import com.serifyasar.kuran_ikelimeler.Activity.DetailActivity
import com.serifyasar.kuran_ikelimeler.Adapter.CategoryRecyAdapter
import com.serifyasar.kuran_ikelimeler.Adapter.LessonRecyAdapter
import com.serifyasar.kuran_ikelimeler.Db.AyetRepository
import com.serifyasar.kuran_ikelimeler.Db.Category
import com.serifyasar.kuran_ikelimeler.Db.CategorySure
import com.serifyasar.kuran_ikelimeler.R
import com.serifyasar.kuran_ikelimeler.Util.catRecyclerClick
import com.serifyasar.kuran_ikelimeler.Util.lessonRecyclerClick
import kotlinx.android.synthetic.main.fragment_kelime.*


class KuranFragment : androidx.fragment.app.Fragment(),catRecyclerClick {

    val repository by lazy { AyetRepository(activity!!.applicationContext) }
    private lateinit var lesList : ArrayList<CategorySure>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_kuran, container, false)


        lesList= listeAsenkronTask(repository).execute().get() as ArrayList<CategorySure>

        val layoutManager= androidx.recyclerview.widget.GridLayoutManager(activity, 2)

        val recyview = view.findViewById(R.id.recyCat) as RecyclerView
        recyview.layoutManager= layoutManager
        recyview.adapter= CategoryRecyAdapter(lesList,this)


        return view
    }


    private class listeAsenkronTask(val repository: AyetRepository) : AsyncTask<Void,Void,List<CategorySure>>() {
        override fun doInBackground(vararg params: Void?): List<CategorySure>? {
            val list =repository.categorySureListe()

            return list
        }

    }

    override fun elemanaTiklanildiginda(category: CategorySure, p: Int) {
        val intent = Intent(activity!!.applicationContext, AyetQuizActivity::class.java)
        intent.putExtra("id",p)
        intent.putExtra("name",category.name)
        startActivity(intent)
    }
}
