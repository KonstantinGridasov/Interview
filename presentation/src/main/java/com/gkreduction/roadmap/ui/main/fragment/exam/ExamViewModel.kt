package com.gkreduction.roadmap.ui.main.fragment.exam

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.usecase.GetListQuestionsByItem
import com.gkreduction.domain.usecase.GetQuestionById
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class ExamViewModel(
    context: Context,
    var getQuestionById: GetQuestionById,
    var getListQuestionByItem: GetListQuestionsByItem
) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    lateinit var item: BaseItem
    private var qa = ArrayList<QuestionAnswer>()
    private var sizeQuestion = MutableLiveData<Int>()
    private var activePosition: Int = -1

    var answer = MutableLiveData<String>()

    var question = MutableLiveData<String>()
    var onRight = MutableLiveData<Int>()
    var onSkip = MutableLiveData<Int>()
    var onBad = MutableLiveData<Int>()
    var statusFinish = MutableLiveData<Boolean>()

    fun updateItem(item: BaseItem) {
        this.item = item
        clearQuestion()
    }

    private fun getQuestionsByItem() {
        if (qa.isEmpty()) {
            val size = (10..20).random()
            val params = GetListQuestionsByItem.Params(item, size = size, random = true)
            viewModelScope.launch {
                getListQuestionByItem.execute(params)
                    .let {
                        qa.addAll(it)
                        sizeQuestion.value = it.size
                        updateQuestion()
                        resetToDefault()
                    }
            }
        }
    }


    fun getAnswerId() {
        val id = qa[activePosition].id
        viewModelScope.launch {
            getQuestionById.execute(id)
                .let {
                    answer.value = it.answer
                }
        }
    }

    fun clearQuestion() {
        resetToDefault()
        qa.clear()
//        question = MutableLiveData<String>()
//        statusFinish = MutableLiveData<Boolean>()
        getQuestionsByItem()
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
            updateStatus()

    }

    private fun updateStatus() {
        activePosition = -1
        val size = sizeQuestion.value ?: 1
        val right = onRight.value ?: 1
        val percent: Float = (right.toFloat() / size.toFloat())
        statusFinish.value = (percent > 0.7)
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

    fun clearAnswer() {
        answer.value = ""
    }


}