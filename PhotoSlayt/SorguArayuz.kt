package com.serifyasar.fotoslayt

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SorguArayuz {

    @Query("select * from photo")
    fun tumResimler() : LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun resimEkle(photo: Photo)

}