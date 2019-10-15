package com.serifyasar.kuran_ikelimeler.Db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Kelimeler  (

    @PrimaryKey(autoGenerate = true)
    var _id : Int=0,
    var categoryId : Int=0,
    var arabic : String="",
    var turkish : String="",
    var kuran : String="",
    var meal : String=""

)