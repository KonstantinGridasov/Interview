package com.gkreduction.interview.ui.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.BaseAndroidViewModel
import com.gkreduction.interview.utils.getPriority
import com.gkreduction.interview.utils.readerScv

class MainViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {

//    val categories = ObservableArrayList<String>()



    fun getQuestionByCategory(cat: String) {
//        Log.e("getQuestionByCategory: ", cat)
//        val set = HashSet<String>()
//        for (data in interviews)
//            if (data.category == cat)
//                set.add(data.question)
//        if (set.size == 0)
//            for (data in interviews)
//                if (data.question == cat)
//                    set.add(data.answer)
//        categories.clear()
//        categories.addAll(set)
    }


}