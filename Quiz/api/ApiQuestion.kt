package com.serifyasar.quiz.api

import com.google.gson.annotations.SerializedName

data class ApiQuestion (
@SerializedName("results")
var sorular : List<Sorular>
)

