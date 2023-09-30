package com.gkreduction.data.repository.db

import com.gkreduction.data.mapper.*
import com.gkreduction.data.repository.db.dao.QuestionAnswerDao
import com.gkreduction.data.repository.db.dao.RoadmapDao
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.entity.*
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

    override suspend fun getQuestionByItem(item: BaseItem?): List<QuestionAnswer> {
        return when (item) {
            is Roadmap -> mapperQAWithRoadmapToModel(qaDao.getQuestionByRoadmapId(item.id))
            is Section -> mapperQAWithSectionToModel(qaDao.getQuestionBySectionId(item.id))
            is Topic -> mapperQAWithTopicToModel(qaDao.getQuestionByTopicId(item.id))
            is Subtopic -> mapperQAWithSubTopicToModel(qaDao.getQuestionBySubTopicTopicId(item.id))
            else -> emptyList()
        }

    }

    override suspend fun getQuestionById(param: Long): QuestionAnswer {
        return transformQADbToModel(qaDao.getQuestionById(param))
    }

    override suspend fun getRandomQuestion(i: Int): List<QuestionAnswer> {
        return transformQAListDbToModel(qaDao.getRandomQuestion(i))
    }
}