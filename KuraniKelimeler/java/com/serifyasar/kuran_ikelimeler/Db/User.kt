package com.serifyasar.kuran_ikelimeler.Db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class User  (

    @PrimaryKey(autoGenerate = true)
    var sid:Int=0,
    var name : String="",
    var level : Int=1,
    var levelQ : Int=1



)