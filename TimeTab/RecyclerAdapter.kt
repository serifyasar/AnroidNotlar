package com.serifyasar.timetable

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast

class RecyclerAdapter(var dersList: ArrayList<Dersler>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), PopupMenu.OnMenuItemClickListener {
private  var id=0
      private lateinit var menuSec: renkSec
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lesson.text = dersList[position].name
        holder.teacher.text = dersList[position].teacher.plus(" ").plus(dersList[position].classroom)
        holder.time1.text = dersList[position].time
        holder.card.setBackgroundColor(ContextCompat.getColor(holder.card.context, dersList[position].renk.toInt()))
        holder.menuButon.tag=dersList[position].id
        holder.menuButon.setOnClickListener {
            id= it.tag as Int
            val popup=PopupMenu(holder.menuButon.context,holder.menuButon)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.context_menu,popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
        }

        try {
            menuSec = holder.itemView.context as renkSec
        }catch (e: Exception){

        }

    }




    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val lesson = view.findViewById<TextView>(R.id.tvDers)
        val teacher = view.findViewById<TextView>(R.id.tvTeacher)
        val time1 = view.findViewById<TextView>(R.id.tvSaat)
        val card = view.findViewById<CardView>(R.id.cardItem)
        val menuButon = view.findViewById<ImageView>(R.id.btnPopup)

     
    }

    fun updateList(newList: ArrayList<Dersler>) {
        dersList.clear()
        dersList.addAll(newList)
        notifyDataSetChanged()
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return  when(item?.itemId){
            R.id.menu_sil -> {

                menuSec.popupMenu("Sil",id)
                true
            }
            else -> false


        }
    }

}
