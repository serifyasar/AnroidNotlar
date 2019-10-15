package com.serifyasar.kuran_ikelimeler.Fragment


import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serifyasar.kuran_ikelimeler.Activity.DetailActivity
import com.serifyasar.kuran_ikelimeler.Adapter.LessonRecyAdapter
import com.serifyasar.kuran_ikelimeler.Db.AyetRepository
import com.serifyasar.kuran_ikelimeler.Db.Category
import com.serifyasar.kuran_ikelimeler.R
import com.serifyasar.kuran_ikelimeler.Util.lessonRecyclerClick



class KelimeFragment : androidx.fragment.app.Fragment(),lessonRecyclerClick {


    val repository by lazy { AyetRepository(activity!!.applicationContext) }
    private lateinit var lesList : ArrayList<Category>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_kelime, container, false)



        lesList= listeAsenkronTask(repository).execute().get() as ArrayList<Category>

        val layoutManager= androidx.recyclerview.widget.GridLayoutManager(activity, 2)

        val recyview = view.findViewById(R.id.recyCat) as RecyclerView
        recyview.layoutManager= layoutManager
        recyview.adapter=LessonRecyAdapter(lesList,this)


        return view
    }


    private class listeAsenkronTask(val repository: AyetRepository) : AsyncTask<Void,Void,List<Category>>() {
        override fun doInBackground(vararg params: Void?): List<Category>? {
            val list =repository.categoryListe()

            return list
        }

    }

    override fun elemanaTiklanildiginda(category: Category, p: Int) {
        val intent = Intent(activity!!.applicationContext, DetailActivity::class.java)
        intent.putExtra("id",p)
        intent.putExtra("name",category.name)
        startActivity(intent)
    }


}
