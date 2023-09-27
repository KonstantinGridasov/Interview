package com.gkreduction.roadmap.ui.main.fragment.answer

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.usecase.GetQuestionById
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class AnswerViewModel(context: Context, var getQuestionById: GetQuestionById) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    var question = MutableLiveData<QuestionAnswer>()

    fun getAnswerByQuestionId(id: Long) {
        viewModelScope.launch {
            getQuestionById.execute(id)
                .let {
                    question.value=it

                }
        }
    }

}