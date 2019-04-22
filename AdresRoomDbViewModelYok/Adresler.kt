package com.serifyasar.adresdefteri

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Adresler  (

    @PrimaryKey(autoGenerate = true)
    var aid : Int=0,
    var name : String,
    var tel : String,
    var adres : String

)