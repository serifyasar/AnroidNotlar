package com.serifyasar.pokemon.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("Biuni/PokemonGO-Pokedex/master/pokedex.json")
    fun tamListe() : Call<PokemonListe>
}