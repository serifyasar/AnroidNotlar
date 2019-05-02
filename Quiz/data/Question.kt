package com.serifyasar.quiz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(

    @PrimaryKey(autoGenerate = true)
    var sid : Int =0,
    var soru : String,
    var cevap_a : String,
    var cevap_b: String,
    var cevap_c: String,
    var cevap_d: String,
    var cevap : String

)