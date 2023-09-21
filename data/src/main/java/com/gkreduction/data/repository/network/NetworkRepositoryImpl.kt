package com.gkreduction.data.repository.network

import com.gkreduction.data.mapper.mapperRemoteListToModelList
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.entity.BaseElement
import com.gkreduction.domain.repository.NetworkRepository
import io.reactivex.Observable

class NetworkRepositoryImpl(var dataStore: NetworkDataStore) : NetworkRepository {

    override fun getRoadmaps(): Observable<List<BaseElement>> {
        return dataStore.getNetworkRoadmaps().map {
            mapperRemoteListToModelList(it)
        }

    }

}