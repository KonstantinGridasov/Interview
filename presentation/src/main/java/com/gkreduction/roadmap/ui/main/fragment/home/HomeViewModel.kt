package com.gkreduction.roadmap.ui.main.fragment.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.usecase.GetRoadmapsUseCase
import com.gkreduction.domain.usecase.UpdateQaUseCase
import com.gkreduction.domain.usecase.UpdateRoadmapsUseCase
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    context: Context,
    var updateRoadmapsUseCase: UpdateRoadmapsUseCase,
    var updateQaUseCase: UpdateQaUseCase,
    var getRoadmapsUseCase: GetRoadmapsUseCase
) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    var roadmaps = MutableLiveData<List<Roadmap>>()

    fun getRoadmapsFromDb() {
        viewModelScope.launch {
            getRoadmapsUseCase.execute()
                .let { roadmaps.value = it }
        }
    }

    fun fetchRoadmaps() {
        viewModelScope.launch {
            updateRoadmapsUseCase.execute()
                .let {
                    if (it)
                        getRoadmapsFromDb()
                }
        }
    }

    fun fetchQA() {
        viewModelScope.launch {
            updateQaUseCase.execute()
                .let {

                }
        }
    }
}