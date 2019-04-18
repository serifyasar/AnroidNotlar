package com.serifyasar.pokemon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.serifyasar.pokemon.data.Pokemon
import com.serifyasar.pokemon.data.PokemonListe
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerAdapter(var gelenUserList:ArrayList<Pokemon>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = gelenUserList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text=gelenUserList[position].name
        holder.height.text="Height :  ${gelenUserList[position].height}"
        holder.weight.text="Weight :  ${gelenUserList[position].weight}"
        holder.candy.text="${gelenUserList[position].candy} | ${gelenUserList[position].type[0]}"
        if(gelenUserList[position].type.size>1)
            holder.candy.text=  holder.candy.text as String + " | " + gelenUserList[position].type[1]
        Picasso.get().load(gelenUserList[position].img).into(holder.image)



    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.textView)
        val height: TextView = view.findViewById(R.id.textView2)
        val weight: TextView = view.findViewById(R.id.textView3)
        val candy: TextView = view.findViewById(R.id.textView4)
        val image:CircleImageView =view.findViewById(R.id.circleImageView)
    }
}