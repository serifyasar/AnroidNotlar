package com.serifyasar.quiz.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SorguDao {

    @Query("select * from question")
    fun tumSorular() : LiveData<List<Question>>

    @Query("select count(*) from question")
    fun soruAdet() : Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun soruEkle(question: Question)

}