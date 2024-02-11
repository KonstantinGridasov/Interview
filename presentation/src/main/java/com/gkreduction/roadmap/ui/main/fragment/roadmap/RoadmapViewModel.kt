package com.gkreduction.roadmap.ui.main.fragment.roadmap

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.ItemRoadmap
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.usecase.GetRoadmapByIdUseCase
import com.gkreduction.roadmap.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class RoadmapViewModel(
    context: Context,
    var getRoadmapByIdUseCase: GetRoadmapByIdUseCase
) :
    BaseAndroidViewModel(context.applicationContext as Application) {
    var roadmap = MutableLiveData<Roadmap>()
    var list = MutableLiveData<List<ItemRoadmap>>()


    fun getRoadmapFull(id: Long) {
        viewModelScope.launch {
            getRoadmapByIdUseCase.execute(id)
                .let {
                    list.value = it
                }
        }
    }

}