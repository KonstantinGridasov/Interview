package com.gkreduction.data.repository.network.datasource

import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.entity.RoadmapRemote
import com.gkreduction.data.repository.network.NetworkApi

class NetworkDataStore(var networkApi: NetworkApi) {
    suspend fun getNetworkRoadmaps(): List<RoadmapRemote> {
        return networkApi.getRoadmaps()
    }

    suspend fun getQa(): List<QuestionAnswerRemote> {
        return networkApi.getQA()
    }
}