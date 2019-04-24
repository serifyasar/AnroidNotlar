package com.serifyasar.fotoslayt

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Photo (

    @PrimaryKey(autoGenerate = true)
    var pid : Int=0,
    var resim : String,
    var detay : String

)