package com.gkreduction.interview.ui.main.fragmnet.question

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import com.gkreduction.interview.entity.DataInfo
import com.gkreduction.interview.entity.Question
import com.gkreduction.interview.utils.BaseAndroidViewModel

class QuestionViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var question = ObservableArrayList<Question>()

    fun initQuestion(cat: String, list: List<DataInfo>) {
        list.sortedBy { it.id }
        val result = ArrayList<Question>()
        for (data in list)
            if (data.category == cat) {
                result.add(Question(data.id, data.question))
            }
        result.sortedBy { it.id }
        question.clear()
        question.addAll(result)
    }
}