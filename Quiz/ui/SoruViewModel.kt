package com.serifyasar.quiz.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.serifyasar.quiz.api.ApiClient
import com.serifyasar.quiz.api.ApiQuestion
import com.serifyasar.quiz.data.Question
import com.serifyasar.quiz.data.Repository
import retrofit2.Call
import retrofit2.Response

class SoruViewModel(application: Application)  : AndroidViewModel(application) {

    private val repository by lazy { Repository(application) }
    var index: Int=0
    var d:Int =0
    var y:Int =0


    val sorular : LiveData<List<Question>> by lazy { repository.tumSorular() }






}