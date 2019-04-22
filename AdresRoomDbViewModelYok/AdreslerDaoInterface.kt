package com.serifyasar.adresdefteri

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AdreslerDaoInterface {
    @Query("select * from adresler")
    fun adresListesi() : List<Adresler>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun adresEkle(adres:Adresler)

}