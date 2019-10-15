package com.serifyasar.kuran_ikelimeler.Db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Ayetler  (

    @PrimaryKey(autoGenerate = true)
    var aid : Int=0,
    var sure : Int=0,
    var ayet : Int=0,
    var arabic : String="",
    var turkish : String="",
    var aword : String="",
    var tword : String=""

)