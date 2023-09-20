package com.gkreduction.roadmap.ui.main.fragment.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.usecase.UpdateQaUseCase
import com.gkreduction.domain.usecase.UpdateRoadmapsUseCase
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    context: Context,
    var getRoadmapsUseCase: UpdateRoadmapsUseCase,
    var updateQaUseCase: UpdateQaUseCase
) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    fun fetchRoadmaps() {
        viewModelScope.launch {
            getRoadmapsUseCase.execute()
                .let {
                    Log.d("fetchQA", " name= $it")
                }
        }
    }

    fun fetchQA() {
        viewModelScope.launch {
            updateQaUseCase.execute()
                .let {
                    Log.d("fetchQA", " name= $it")

                }
        }
    }
}