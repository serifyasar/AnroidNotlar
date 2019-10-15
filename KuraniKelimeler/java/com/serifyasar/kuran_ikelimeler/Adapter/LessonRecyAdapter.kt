package com.serifyasar.kuran_ikelimeler.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.serifyasar.kuran_ikelimeler.Db.AyarRepository
import com.serifyasar.kuran_ikelimeler.Db.Category
import com.serifyasar.kuran_ikelimeler.Db.User
import com.serifyasar.kuran_ikelimeler.R
import com.serifyasar.kuran_ikelimeler.Util.lessonRecyclerClick



class LessonRecyAdapter(var gelenUserList:ArrayList<Category> , var recyclerTiklama: lessonRecyclerClick) : androidx.recyclerview.widget.RecyclerView.Adapter<LessonRecyAdapter.ViewHolder>() {


    private var level=1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.lesson_item,parent,false)

       val repository= AyarRepository(parent.context)


        if(repository.userCount()>0){

            level=repository.getUser().level

        }
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = gelenUserList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text=gelenUserList[position].name

        if(position+1>level){
            holder.img.setImageResource(R.drawable.oval_gray)
            holder.img.isClickable=true
            holder.imgLock.visibility=View.VISIBLE
        }else{
            holder.img.setImageResource(R.drawable.oval)
            holder.imgLock.visibility=View.GONE
            holder.img.isClickable=false
        }

        holder.itemView.setOnClickListener{   //ilgili eleman tıklanıldığında
           recyclerTiklama.elemanaTiklanildiginda(gelenUserList[position],position)
        }


    }


    class ViewHolder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.item_tv_name)
        val img: ImageView = view.findViewById(R.id.item_oval_img)
        val imgLock: ImageView = view.findViewById(R.id.item_lock_img)

    }
}