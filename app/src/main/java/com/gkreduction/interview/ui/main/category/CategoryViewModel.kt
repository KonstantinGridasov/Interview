package com.gkreduction.interview.ui.main.category

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.BaseAndroidViewModel
import com.gkreduction.interview.utils.getPriority
import com.gkreduction.interview.utils.readerScv

class CategoryViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var categories = ObservableArrayList<String>()
    val dataInfo = MutableLiveData<List<DataInfo>>()



    fun readFile() {
        val list = readerScv(context)
        val set = HashSet<String>()
        for (data in list)
            set.add(data.category)
        val result = ArrayList<String>(set)
        result.sortBy { getPriority(it) }
        categories.addAll(result)
        dataInfo.value = list

    }
}