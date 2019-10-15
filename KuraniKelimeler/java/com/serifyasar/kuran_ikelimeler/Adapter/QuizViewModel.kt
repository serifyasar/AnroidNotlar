package com.serifyasar.kuran_ikelimeler.Adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serifyasar.kuran_ikelimeler.Util.Word

class QuizViewModel : ViewModel() {
    private  val _durum= MutableLiveData<Int>()


    var durum : MutableLiveData<Int>? = null
        get() = _durum

}