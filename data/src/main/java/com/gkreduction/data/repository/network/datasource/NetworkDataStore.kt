package com.gkreduction.data.repository.network.datasource

import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.entity.RoadmapRemote
import com.gkreduction.data.repository.network.NetworkApi
import io.reactivex.Observable

class NetworkDataStore(var networkApi: NetworkApi) {
     fun getNetworkRoadmaps(): Observable<List<RoadmapRemote>> {
        return networkApi.getRoadmaps()
    }

     fun getQa(): Observable<List<QuestionAnswerRemote>> {
        return networkApi.getQA()
    }
}