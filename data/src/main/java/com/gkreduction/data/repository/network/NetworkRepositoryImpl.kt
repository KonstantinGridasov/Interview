package com.gkreduction.data.repository.network

import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.repository.NetworkRepository

class NetworkRepositoryImpl(var dataStore: NetworkDataStore) : NetworkRepository {

    override suspend fun getRoadmaps(): List<Any> {
//        val data = dataStore.getNetworkRoadmaps()
//        return if (data.isNotEmpty())
//            mapperRemoteListToModelList(data)
        return emptyList()

    }

}