package com.gkreduction.data.repository

import com.gkreduction.data.mapper.mapListRoadmap
import com.gkreduction.data.repository.datasource.NetworkDataStore
import com.gkreduction.domain.entity.BaseElement
import com.gkreduction.domain.repository.NetworkRepository

class NetworkRepositoryImpl(var dataStore: NetworkDataStore) : NetworkRepository {

    override suspend fun getRoadmaps(): List<BaseElement> {
        val data = dataStore.getNetworkRoadmaps()
        return if (data.isNotEmpty())
            mapListRoadmap(data)
        else emptyList()

    }

}