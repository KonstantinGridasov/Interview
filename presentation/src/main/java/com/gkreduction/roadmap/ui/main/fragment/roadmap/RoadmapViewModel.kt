package com.gkreduction.roadmap.ui.main.fragment.roadmap

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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


    fun getRoadmapFull(id: Long) {
        viewModelScope.launch {
            getRoadmapByIdUseCase.execute(id)
                .let {
                    roadmap.value=it
//                    Log.d("getRoadmapFull", it.name)
//                    Log.d("getRoadmapFull", "section size " + it.section.size)
//                    for (sec in it.section) {
//                        Log.d("getRoadmapFull", "section " + sec.name)
//                        for (topic in sec.topics) {
//                            Log.d("getRoadmapFull", "topic " + topic.name)
//                            for (subtopic in topic.subtopics)
//                                Log.d("getRoadmapFull", "topic " + subtopic.name)
//                        }



                }
        }
    }

}