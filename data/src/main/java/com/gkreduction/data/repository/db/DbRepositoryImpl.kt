package com.gkreduction.data.repository.db

import com.gkreduction.data.mapper.mapperRoadmapsDbToCore
import com.gkreduction.data.mapper.transformFullRoadmapDB
import com.gkreduction.data.repository.db.dao.QuestionAnswerDao
import com.gkreduction.data.repository.db.dao.RoadmapDao
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.repository.DbRepository

class DbRepositoryImpl(
    var roadmapDao: RoadmapDao,
    var qaDao: QuestionAnswerDao,
    var network: NetworkDataStore
) : DbRepository {


    override suspend fun updateQuestion(): Boolean {
        qaDao.insert(network.getQa())
        return true
    }

    override suspend fun updateDb(): Boolean {
        roadmapDao.insert(network.getNetworkRoadmaps())
        return true

    }

    override suspend fun getRoadmaps(): List<Roadmap> {
        return mapperRoadmapsDbToCore(roadmapDao.getAllRoadmaps())
    }

    override suspend fun getRoadmapById(id: Long): Roadmap {
        return transformFullRoadmapDB(roadmapDao.getFullRoadmap(id))
    }
}