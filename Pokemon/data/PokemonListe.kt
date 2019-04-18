package com.serifyasar.pokemon.data

import com.google.gson.annotations.SerializedName

data class PokemonListe (

    @SerializedName("pokemon")
    var pokemonListe : ArrayList<Pokemon>

)