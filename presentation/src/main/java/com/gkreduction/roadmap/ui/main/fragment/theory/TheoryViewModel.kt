package com.gkreduction.roadmap.ui.main.fragment.theory

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.usecase.GetListQuestionsByItem
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class TheoryViewModel(context: Context, var getListQuestionByItem: GetListQuestionsByItem) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    var answers: List<QuestionAnswer> = emptyList()
    var answer = MutableLiveData<QuestionAnswer>()
    var position: Int = 0

    fun getTheoryByItem(item: BaseItem, id: Long) {
        viewModelScope.launch {
            val params = GetListQuestionsByItem.Params(item)
            getListQuestionByItem.execute(params)
                .let {
                    if (it.isNotEmpty()) {
                        answers = it
                        if (id > 0) {
                            setPosition(id)
                        } else
                            position = 0
                        updateAnswer()

                    } else {
                        answer.value = QuestionAnswer(-1L, item.name, "TODO", 0)
                        answers = emptyList()
                    }
                }
        }
    }

    private fun setPosition(id: Long) {
        for (i in answers.indices) {
            if (answers[i].id == id)
                position = i
        }
    }

    fun onPreviewAnswer() {
        if (position == 0)
            position = answers.size - 1
        else if (position > 0)
            position--
        else
            position = 0

        updateAnswer()

    }

    fun onNextAnswer() {
        if (position == answers.size - 1)
            position = 0
        else if (position >= 0)
            position++
        else
            position = 0
        updateAnswer()
    }


    private fun updateAnswer() {
        if (position >= 0 && position <= answers.size - 1)
            answer.value = answers[position]
    }

}