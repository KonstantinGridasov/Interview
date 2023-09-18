package com.gkreduction.data.repository.db

import com.gkreduction.data.repository.db.dao.QuestionAnswerDao
import com.gkreduction.data.repository.db.dao.RoadmapDao
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.repository.DbRepository

class DbRepositoryImpl(
    var roadmapDao: RoadmapDao,
    var qaDao: QuestionAnswerDao,
    var network: NetworkDataStore
) : DbRepository {


    override suspend fun updateDb(): Boolean {
        qaDao.insert(network.getQa())
        return true
    }
}