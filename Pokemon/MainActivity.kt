package com.serifyasar.pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.serifyasar.pokemon.data.ApiClient
import com.serifyasar.pokemon.data.ApiService
import com.serifyasar.pokemon.data.PokemonListe
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val api  by lazy { ApiClient.apiCagir()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        progressBar.visibility=View.VISIBLE
        recyclerView.visibility=View.GONE


        api.tamListe().enqueue(object : Callback<PokemonListe>{
            override fun onFailure(call: Call<PokemonListe>, t: Throwable) {
                Log.d("Hata",t.message)
            }

            override fun onResponse(call: Call<PokemonListe>, response: Response<PokemonListe>) {
                if(response.isSuccessful){
                    recyclerView.adapter=RecyclerAdapter(response.body()!!.pokemonListe)
                    recyclerView.visibility=View.VISIBLE
                    progressBar.visibility=View.GONE
                }
            }

        })

    }
}
