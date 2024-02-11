package com.gkreduction.data.repository.db

import com.gkreduction.data.mapper.*
import com.gkreduction.data.repository.db.dao.QuestionAnswerDao
import com.gkreduction.data.repository.db.dao.RoadmapDao
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.entity.ItemRoadmap
import com.gkreduction.domain.entity.QuestionAnswer
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.entity.TypeItem
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

    override suspend fun getRoadmapById(id: Long): List<ItemRoadmap> {
        return transformFullRoadmapDB(roadmapDao.getFullRoadmap(id))
    }

    override suspend fun getQuestionByItem(
        item: ItemRoadmap,
        random: Boolean, size: Int
    ): List<QuestionAnswer> {
        return when (item.type) {
            TypeItem.ROADMAP -> mapperQAWithRoadmapToModel(
                qaDao.getQuestionByRoadmapId(item.id),
                random,
                size
            )
            TypeItem.SECTION -> mapperQAWithSectionToModel(
                qaDao.getQuestionBySectionId(item.id),
                random,
                size
            )
            TypeItem.TOPIC -> mapperQAWithTopicToModel(
                qaDao.getQuestionByTopicId(item.id),
                random,
                size
            )
            TypeItem.SUBTOPIC -> mapperQAWithSubTopicToModel(
                qaDao.getQuestionBySubTopicTopicId(item.id),
                random, size
            )
            else -> emptyList()
        }

    }

    override suspend fun getQuestionById(param: Long): QuestionAnswer {
        return transformQADbToModel(qaDao.getQuestionById(param))
    }

}