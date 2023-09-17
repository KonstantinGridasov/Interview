package com.gkreduction.data.repository.datasource

import com.gkreduction.data.entity.RoadmapRemote
import com.gkreduction.data.repository.NetworkApi

class NetworkDataStore(var networkApi: NetworkApi) {
    suspend fun getNetworkRoadmaps(): List<RoadmapRemote> {
        return networkApi.getRoadmaps()
    }

}