package com.gkreduction.roadmap.ui.main.fragment.exam

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.usecase.GetQuestionById
import com.gkreduction.domain.usecase.GetRandomQuestion
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class ExamViewModel(context: Context, var getRandomQuestion: GetRandomQuestion,
                    var getQuestionById: GetQuestionById
) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    private var qa = ArrayList<QuestionAnswer>()
    private var sizeQuestion = MutableLiveData<Int>()
    private var activePosition: Int = -1
    var answer = MutableLiveData<QuestionAnswer>()

    var question = MutableLiveData<String>()
    var onRight = MutableLiveData<Int>()
    var onSkip = MutableLiveData<Int>()
    var onBad = MutableLiveData<Int>()


    fun getQuestions() {
        val items = (20..30).random()
        viewModelScope.launch {
            getRandomQuestion.execute(items)
                .let {
                    qa.addAll(it)
                    sizeQuestion.value = it.size
                    resetToDefault()
                    updateQuestion()

                }
        }

    }


    fun getAnswerId() {
        val id = qa[activePosition].id
        viewModelScope.launch {
            getQuestionById.execute(id)
                .let {
                    answer.value = it
                }
        }
    }

    private fun resetToDefault() {
        onSkip.value = 0
        onRight.value = 0
        onBad.value = 0
    }

    private fun updateQuestion() {
        activePosition++
        if (activePosition >= 0 && activePosition < qa.size - 1)
            question.value = qa[activePosition].question
        else
            getQuestions()
    }

    fun onSkipClick() {
        var value = onSkip.value ?: 0
        value++
        onSkip.value = value
        updateQuestion()
    }

    fun onRightClick() {
        var value = onRight.value ?: 0
        value++
        onRight.value = value
        updateQuestion()
    }

    fun onBadClick() {
        var value = onBad.value ?: 0
        value++
        onBad.value = value
        updateQuestion()

    }

    fun getTitleByItem(item: Int): String {
        return "${sizeQuestion.value ?: 0}/$item "
    }


}