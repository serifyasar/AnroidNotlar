package com.serifyasar.fotoslayt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ResimViewModel(application: Application)  : AndroidViewModel(application) {

    private val repository by lazy { Repository(application) }
    var index: Int=0

    val resimler : LiveData<List<Photo>> by lazy { repository.tumResimler() }

}