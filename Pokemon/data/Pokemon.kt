package com.serifyasar.pokemon.data

import com.google.gson.annotations.SerializedName

data class Pokemon(

@SerializedName("name")
var name : String,
@SerializedName("img")
var img : String,
@SerializedName("height")
var height : String,
@SerializedName("weight")
var weight : String,
@SerializedName("candy")
var candy : String,
@SerializedName("type")
var type : List<String>

)