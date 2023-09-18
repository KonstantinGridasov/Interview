package com.gkreduction.data.repository.db.dao

import androidx.room.*
import com.gkreduction.data.entity.QADataRemote
import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.mapper.*
import com.gkreduction.data.repository.db.entity.question.*

@Dao
interface QuestionAnswerDao {

    @Transaction
    suspend fun insert(qaList: List<QuestionAnswerRemote>) {
        for (qa in qaList) {
            val qaId = initQuestionAnswerDb(qa)
            for (road in qa.roadmap) {
                val roadmapId = initQADataRoadmapDb(road)
                initDataCrossRef(qaId, roadmapId)
            }
            for (section in qa.section) {
                val sectionId = initQADataSection(section)
                initDataCrossRef(qaId, sectionId)
            }
            for (topic in qa.topic) {
                val topicId = initQADataTopic(topic)
                initDataCrossRef(qaId, topicId)
            }
            for (subtopic in qa.subtopic) {
                val subtopicId = initQADataSubtopic(subtopic)
                initDataCrossRef(qaId, subtopicId)
            }
        }


    }

    //region QuestionAnswerDb
    @Transaction
    suspend fun initQuestionAnswerDb(qa: QuestionAnswerRemote): Long {
        val qaDb = transformRemoteQaToDb(qa)
        return if (isExistsQa(qaDb.qaId)) {
            update(qaDb)
            qaDb.qaId
        } else
            insert(qaDb)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qa: QuestionAnswerDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(qa: QuestionAnswerDb)

    @Query("SELECT EXISTS(SELECT * FROM qa_db WHERE qaId = :qaId)")
    suspend fun isExistsQa(qaId: Long): Boolean

    //endregion

    //region QADataRoadmapDb
    @Transaction
    suspend fun initQADataRoadmapDb(data: QADataRemote): Long {
        val qaDataRoadmap = transformQADataToRoadmapDb(data)
        return if (isExistsQaRoadmap(qaDataRoadmap.dataId)) {
            update(qaDataRoadmap)
            qaDataRoadmap.dataId
        } else
            insert(qaDataRoadmap)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaRoadmap: QADataRoadmapDb): Long

    @Query("SELECT EXISTS(SELECT * FROM qa_data_roadmap_db WHERE dataId = :dataId)")
    fun isExistsQaRoadmap(dataId: Long): Boolean

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(qa: QADataRoadmapDb)
    //endregion

    //region QADataSectionDb
    @Transaction
    suspend fun initQADataSection(data: QADataRemote): Long {
        val qaDataSection = transformQADataToSectionDb(data)
        return if (isExistsQADataSection(qaDataSection.dataId)) {
            update(qaDataSection)
            qaDataSection.dataId
        } else
            insert(qaDataSection)
    }

    @Query("SELECT EXISTS(SELECT * FROM qa_data_section_db WHERE dataId = :dataId)")
    suspend fun isExistsQADataSection(dataId: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaSection: QADataSectionDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(qa: QADataSectionDb)

    //endregion

    //region QADataTopicDb
    @Transaction
    suspend fun initQADataTopic(data: QADataRemote): Long {
        val qaDataTopic = transformQADataToTopicDb(data)
        return if (isExistsQADataTopic(qaDataTopic.dataId)) {
            update(qaDataTopic)
            qaDataTopic.dataId
        } else
            insert(qaDataTopic)
    }

    @Query("SELECT EXISTS(SELECT * FROM qa_data_topic_db WHERE dataId = :dataId)")
    suspend fun isExistsQADataTopic(dataId: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaTopic: QADataTopicDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(qa: QADataTopicDb)

    //endregion

    //region QADataSubtopicDb
    @Transaction
    suspend fun initQADataSubtopic(data: QADataRemote): Long {
        val qaDataTopic = transformQADataToSubtopicDb(data)
        return if (isExistsQADataSubtopic(qaDataTopic.dataId)) {
            update(qaDataTopic)
            qaDataTopic.dataId
        } else
            insert(qaDataTopic)
    }

    @Query("SELECT EXISTS(SELECT * FROM qa_data_subtopic_db WHERE dataId = :dataId)")
    suspend fun isExistsQADataSubtopic(dataId: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaSubtopic: QADataSubtopicDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(qa: QADataSubtopicDb)

    //endregion

    //region QAWithDataCrossRef
    @Transaction
    suspend fun initDataCrossRef(qaId: Long, dataId: Long) {
        if (!isExistsDataCrossRef(qaId, dataId))
            insert(QAWithDataCrossRef(qaId, dataId))

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaCross: QAWithDataCrossRef): Long

    @Query("SELECT EXISTS(SELECT * FROM qa_with_data_db WHERE dataId LIKE :dataId AND qaId LIKE :qaId )")
    suspend fun isExistsDataCrossRef(qaId: Long, dataId: Long): Boolean

    //endregion
}