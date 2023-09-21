package com.gkreduction.roadmap.ui.main.fragment.exam

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import com.gkreduction.roadmap.entity.DataInfo
import com.gkreduction.roadmap.ui.base.BaseAndroidViewModel

class ExamViewModel(context: Context) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    private var list = ArrayList<DataInfo>()
    var answer = ObservableField<String>()
    var question = ObservableField<String>()

    fun update(dataInfo: List<DataInfo>?) {
        if (dataInfo != null) {
            val list = ArrayList<DataInfo>()
            for (i in dataInfo)
                if (addToExam(i.category))
                    list.add(i)
            this.list.addAll(list)
        }

    }

    fun updateRandom() {
        val random = (list.indices).random()
        question.set(list[random].question)
        answer.set(list[random].answer)
    }

    private fun addToExam(cat: String): Boolean {
        return when (cat) {
            "ООП" -> true
            "Design patterns." -> true
            "Java" -> true
            "Kotlin" -> true
            "RxJava" -> true
            "Android" -> true
            else -> false
        }
    }
}