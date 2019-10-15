package com.serifyasar.kuran_ikelimeler.Adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serifyasar.kuran_ikelimeler.Util.Lesson

class CategoryViewModel : ViewModel() {

   private  val _lesson= MutableLiveData<Lesson>()


    val lesson : MutableLiveData<Lesson>
    get() = _lesson
}