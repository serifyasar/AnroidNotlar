package com.serifyasar.kuran_ikelimeler.Db


import androidx.room.*

@Dao
interface DaoInterface {
    @Query("select * from ayetler")
    fun ayetListe() : List<Ayetler>


    @Update
    fun userUpdate(user: User)

    @Query("select * from kelimeler where categoryId=:k_id")
    fun kelimeListe(k_id:Int) : List<Kelimeler>

    @Query("select * from category")
    fun categoryListe() : List<Category>

    @Query("select * from categorysure")
    fun categorySureListe() : List<CategorySure>

    @Query("select count(*) from user")
    fun userCount() : Int

    @Query("select count(*) from category")
    fun categoryCount() : Int

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun setStatus(status: Status)

    @Query("select count(*) from categorysure")
    fun categorySureCount() : Int

    @Query("delete from ayetler")
    fun ayetleriSil()

    @Query("delete from user")
    fun userDelete()

    @Query("delete from kelimeler")
    fun kelimeleriSil()

    @Query("delete from status")
    fun statusSil()

    @Query("delete from category")
    fun categorySil()

    @Query("delete from categorysure")
    fun categorySureSil()

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun ayetEkle(ayet: Ayetler)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun kelimeEkle(kelime: Kelimeler)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun categoryEkle(category: Category)

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun categorySureEkle(categorySure: CategorySure)

    @Query("select * from user limit 1")
    fun getUser() : User

    @Query("select level from status limit 1")
    fun getStatus() : Int




}