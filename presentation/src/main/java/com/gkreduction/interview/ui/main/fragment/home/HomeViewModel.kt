package com.gkreduction.interview.ui.main.fragment.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.usecase.GetRoadmapsUseCase
import com.gkreduction.interview.utils.BaseAndroidViewModel
import kotlinx.coroutines.launch

class HomeViewModel(context: Context, var getRoadmapsUseCase: GetRoadmapsUseCase) :
    BaseAndroidViewModel(context.applicationContext as Application) {

    fun fetchRoadmaps() {
        viewModelScope.launch {
            getRoadmapsUseCase.execute()
                .let {
                    when (it[0]) {
                        is Roadmap -> {
                            val roadmap = it[0] as Roadmap
                            println(roadmap)
                            Log.d("fetchRoadmaps", " name= " + roadmap.name)
                            Log.d("fetchRoadmaps", " size= " + roadmap.section.size)
                        }

                    }
                }
        }
    }
}