package com.gkreduction.interview.ui.main

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.gkreduction.interview.entity.Category
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.BaseAndroidViewModel
import com.gkreduction.interview.utils.getPriority
import com.gkreduction.interview.utils.readerJson

class MainViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var categories = ObservableArrayList<Category>()
    val dataInfo = MutableLiveData<List<DataInfo>>()

//    val categories = ObservableArrayList<String>()


    fun readFile() {

        val list = readerJson(context)
        val set = HashSet<String>()
        for (data in list) {
            set.add(data.category)
        }
        val result = ArrayList<Category>()
        for (cat in set) {
            val category = Category(getPriority(cat), cat)
            result.add(category)
        }
        result.sortBy { it.id }
        categories.addAll(result)
        dataInfo.value = list

    }

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