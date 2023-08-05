package com.gkreduction.interview.ui.main.fragmnet.answer

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.utils.BaseAndroidViewModel

class AnswerViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var answer = ObservableField<String>()
    var question = ObservableField<String>()
    fun getAnswer(id: Int, list: List<DataInfo>) {
        for (data in list) {
            if (data.id == id) {
                answer.set(data.answer)
                question.set(data.question)
            }
        }
    }
}