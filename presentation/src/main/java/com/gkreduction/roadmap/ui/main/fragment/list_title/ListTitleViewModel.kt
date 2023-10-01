package com.gkreduction.roadmap.ui.main.fragment.list_title

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.BaseItem
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.usecase.GetListQuestionsByItem
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class ListTitleViewModel(
    context: Context,
    var getListQuestionByItem: GetListQuestionsByItem
) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var roadmap = MutableLiveData<List<QuestionAnswer>>()


    fun getQuestionsById(item: BaseItem) {
        viewModelScope.launch {
            getListQuestionByItem.execute(item)
                .let {
                    roadmap.value = it

                }
        }
    }

}