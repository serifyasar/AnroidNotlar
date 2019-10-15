package com.serifyasar.kuran_ikelimeler.Db

import android.content.Context
import android.os.AsyncTask

class AyarRepository(context: Context) {

    private   val db by lazy { Veritabani.getInstance(context) }
    private val sorgu by lazy { db.ayetDao() }






    fun getUser() : User = userAsenkronTask(sorgu).execute().get()!!
    private class userAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,User?>(){
        override fun doInBackground(vararg params: Void?): User? {

            val user= sorgu.getUser()

            return user
        }

    }

    fun getStatus() : Int = statusAsenkronTask(sorgu).execute().get()!!
    private class statusAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Int?>(){
        override fun doInBackground(vararg params: Void?): Int? {

            val status= sorgu.getStatus()

            return status
        }

    }

    fun setStatus( s:Status) = setStatusAsenkronTask(sorgu).execute(s)
    private class setStatusAsenkronTask(val sorgu : DaoInterface): AsyncTask<Status,Void,Void>(){
        override fun doInBackground(vararg params:com.serifyasar.kuran_ikelimeler.Db.Status?): Void? {

            sorgu.setStatus(params[0]!!)
            return null
        }

    }

    fun userCount() : Int = countAsenkronTask(sorgu).execute().get()!!
    private class countAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Int>(){
        override fun doInBackground(vararg params: Void?): Int? {

            val adet= sorgu.userCount()
            return adet
        }

    }


    fun categoryCount() : Int = categoryCountAsenkronTask(sorgu).execute().get()!!
    private class categoryCountAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Int>(){
        override fun doInBackground(vararg params: Void?): Int? {

            val adet= sorgu.categoryCount()
            return adet
        }

    }


    fun categorySureCount() : Int = categorySureCountAsenkronTask(sorgu).execute().get()!!
    private class categorySureCountAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Int>(){
        override fun doInBackground(vararg params: Void?): Int? {

            val adet= sorgu.categorySureCount()
            return adet
        }

    }
    fun categoryEkle( category: Category) = insertAsenkronTask(sorgu).execute(category)
    private class insertAsenkronTask(val sorgu : DaoInterface): AsyncTask<Category,Void,Void>(){
        override fun doInBackground(vararg params: Category?): Void? {

            sorgu.categoryEkle(params[0]!!)
            return null
        }

    }

    fun categorySureEkle( categorySure: CategorySure) = insertCatAsenkronTask(sorgu).execute(categorySure)
    private class insertCatAsenkronTask(val sorgu : DaoInterface): AsyncTask<CategorySure,Void,Void>(){
        override fun doInBackground(vararg params: CategorySure?): Void? {

            sorgu.categorySureEkle(params[0]!!)
            return null
        }

    }



    fun addUser( user: User) = insertUserAsenkronTask(sorgu).execute(user)
    private class insertUserAsenkronTask(val sorgu : DaoInterface): AsyncTask<User,Void,Void>(){
        override fun doInBackground(vararg params: User?): Void? {

            sorgu.addUser(params[0]!!)
            return null
        }

    }


    fun userUpdate( user: User) = userUpdateTask(sorgu).execute(user)
    private class userUpdateTask(val sorgu : DaoInterface): AsyncTask<User,Void,Void>(){
        override fun doInBackground(vararg params: User?): Void? {

            sorgu.userUpdate(params[0]!!)
            return null
        }

    }


    fun categorySil() = deleteCategoryAsenkronTask(sorgu).execute()
    private class deleteCategoryAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {

            sorgu.categorySil()
            return null
        }

    }


    fun userDelete() = deleteUserAsenkronTask(sorgu).execute()
    private class deleteUserAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {

            sorgu.userDelete()
            return null
        }

    }
    fun categorySureSil() = deleteCategorySureAsenkronTask(sorgu).execute()
    private class deleteCategorySureAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {

            sorgu.categorySureSil()
            return null
        }

    }

    fun statusSil() = deleteStatusAsenkronTask(sorgu).execute()
    private class deleteStatusAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg params: Void?): Void? {

            sorgu.statusSil()
            return null
        }

    }

    //  fun ayetEkle( ayet:Ayetler) = insertAsenkronTask(sorgu).execute(ayet)
  //  fun ayetSil() = deleteAsenkronTask(sorgu).execute()


/*
    private class insertAsenkronTask(val sorgu : DaoInterface): AsyncTask<Ayetler,Void,Void>(){
        override fun doInBackground(vararg params: Ayetler?): Void? {

            sorgu.ayetEkle(params[0]!!)
            return null
        }

    }


    private class countAsenkronTask(val sorgu : DaoInterface): AsyncTask<Void,Void,Int>(){
        override fun doInBackground(vararg params: Void?): Int? {

            val adet= sorgu.ayetAdet()
            return adet
        }

    }

    */
}