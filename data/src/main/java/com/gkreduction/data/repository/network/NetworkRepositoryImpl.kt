package com.gkreduction.data.repository.network

import com.gkreduction.data.mapper.mapperRemoteListToModelList
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.entity.BaseElement
import com.gkreduction.domain.repository.NetworkRepository

class NetworkRepositoryImpl(var dataStore: NetworkDataStore) : NetworkRepository {

    override suspend fun getRoadmaps(): List<BaseElement> {
        val data = dataStore.getNetworkRoadmaps()
        return if (data.isNotEmpty())
            mapperRemoteListToModelList(data)
        else emptyList()

    }

}