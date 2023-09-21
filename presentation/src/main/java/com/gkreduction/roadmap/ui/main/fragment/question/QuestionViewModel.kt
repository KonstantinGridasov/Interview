package com.gkreduction.roadmap.ui.main.fragment.question

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import com.gkreduction.roadmap.entity.DataInfo
import com.gkreduction.roadmap.entity.Question
import com.gkreduction.roadmap.ui.base.BaseAndroidViewModel

class QuestionViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var question = ObservableArrayList<Question>()
    var position: Int = 0

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