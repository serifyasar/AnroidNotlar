package com.serifyasar.quiz.api

import com.google.gson.annotations.SerializedName

data class Sorular (

    @SerializedName("question")
    var question : String,
    @SerializedName("correct_answer")
    var answer : String,
    @SerializedName("incorrect_answers")
    var other : List<String>


)