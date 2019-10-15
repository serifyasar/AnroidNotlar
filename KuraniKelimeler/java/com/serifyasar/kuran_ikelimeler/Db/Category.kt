package com.serifyasar.kuran_ikelimeler.Db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Category  (

    @PrimaryKey(autoGenerate = true)
    var cid : Int=0,
    var lock : Int=0,
    var name : String=""

)