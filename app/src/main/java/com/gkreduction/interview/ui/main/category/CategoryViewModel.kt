package com.gkreduction.interview.ui.main.category

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.gkreduction.interview.entity.Category
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.BaseAndroidViewModel
import com.gkreduction.interview.utils.getPriority
import com.gkreduction.interview.utils.readerScv

class CategoryViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var categories = ObservableArrayList<Category>()
    val dataInfo = MutableLiveData<List<DataInfo>>()


    fun readFile() {
        val list = readerScv(context)
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
}