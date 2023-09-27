package com.gkreduction.roadmap.ui.dialog.finish

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.usecase.GetQuestionById
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class FinishViewModel(
    context: Context,
) : BaseAndroidViewModel(context.applicationContext as Application) {


}