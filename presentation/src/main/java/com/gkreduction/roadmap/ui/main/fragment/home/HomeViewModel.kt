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
import com.gkreduction.roadmap.utils.pingServer
import com.gkreduction.roadmap.utils.statusNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    context: Context,
    var updateRoadmapsUseCase: UpdateRoadmapsUseCase,
    var updateQaUseCase: UpdateQaUseCase,
    var getRoadmapsUseCase: GetRoadmapsUseCase
) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    var roadmaps = MutableLiveData<List<Roadmap>>()

    var availableServer = MutableLiveData<Boolean>()
    var command: () -> Unit = {}

    private fun serverIsAvailable() {
        viewModelScope.launch(Dispatchers.IO) {
            availableServer.postValue(statusNetwork(context) && pingServer())
        }
    }


    fun update(unit: () -> Unit) {
        command = unit
        viewModelScope.launch {
            serverIsAvailable()
        }
    }

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