package com.gkreduction.interview.ui.main.list

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.BaseAndroidViewModel
import com.gkreduction.interview.utils.readerScv

class ListViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    val interviews = ObservableArrayList<DataInfo>()
    fun readFile() {
        interviews.addAll(readerScv())
    }
}