package com.gkreduction.data.repository.db.dao

import androidx.room.*
import com.gkreduction.data.entity.QADataRemote
import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.mapper.*
import com.gkreduction.data.repository.db.entity.question.*

@Dao
interface QuestionAnswerDao {

    @Query("SELECT * FROM qa_db WHERE qaId = :id")
    suspend fun getQuestionById(id: Long): QuestionAnswerDb


    @Transaction
    @Query("SELECT * FROM qa_data_section_db WHERE dataId = :id")
    suspend fun getQuestionBySectionId(id: Long): SectionWithQa

    @Transaction
    @Query("SELECT * FROM qa_data_topic_db WHERE dataId = :id")
    suspend fun getQuestionByTopicId(id: Long): TopicWithQa

    @Transaction
    @Query("SELECT * FROM qa_data_subtopic_db WHERE dataId = :id")
    suspend fun getQuestionBySubTopicTopicId(id: Long): SubtopicWithQa

    @Transaction
    suspend fun insert(qaList: List<QuestionAnswerRemote>) {
        for (qa in qaList) {
            val qaId = initQuestionAnswerDb(qa)
            for (road in qa.roadmap) {
                val roadmapId = initQADataRoadmapDb(road)
                initQAWithRoadmapCrossRef(qaId, roadmapId)
            }
            for (section in qa.section) {
                val sectionId = initQADataSection(section)
                initQAWithSectionCrossRef(qaId, sectionId)
            }
            for (topic in qa.topic) {
                val topicId = initQADataTopic(topic)
                initQAWithTopicCrossRef(qaId, topicId)
            }
            for (subtopic in qa.subtopic) {
                val subtopicId = initQADataSubtopic(subtopic)
                initQAWithSubTopicCrossRef(qaId, subtopicId)
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
    suspend fun isExistsQaRoadmap(dataId: Long): Boolean

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

    //region QAWithRoadmapCrossRef
    @Transaction
    suspend fun initQAWithRoadmapCrossRef(qaId: Long, dataId: Long) {
        if (!isExistsQAWithRoadmapCrossRef(qaId, dataId))
            insert(QAWithRoadmapCrossRef(qaId, dataId))

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaCross: QAWithRoadmapCrossRef): Long

    @Query("SELECT EXISTS(SELECT * FROM cross_ref_qa_roadmap WHERE dataId LIKE :dataId AND qaId LIKE :qaId )")
    suspend fun isExistsQAWithRoadmapCrossRef(qaId: Long, dataId: Long): Boolean

    //endregion


    //region QAWithSectionCrossRef
    @Transaction
    suspend fun initQAWithSectionCrossRef(qaId: Long, dataId: Long) {
        if (!isExistsQAWithSectionCrossRef(qaId, dataId))
            insert(QAWithSectionCrossRef(qaId, dataId))

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaCross: QAWithSectionCrossRef): Long

    @Query("SELECT EXISTS(SELECT * FROM cross_ref_qa_section WHERE dataId LIKE :dataId AND qaId LIKE :qaId )")
    suspend fun isExistsQAWithSectionCrossRef(qaId: Long, dataId: Long): Boolean

    //endregion


    //region QAWithTopicCrossRef
    @Transaction
    suspend fun initQAWithTopicCrossRef(qaId: Long, dataId: Long) {
        if (!isExistsQAWithTopicCrossRef(qaId, dataId))
            insert(QAWithTopicCrossRef(qaId, dataId))

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaCross: QAWithTopicCrossRef): Long

    @Query("SELECT EXISTS(SELECT * FROM cross_ref_qa_topic WHERE dataId LIKE :dataId AND qaId LIKE :qaId )")
    suspend fun isExistsQAWithTopicCrossRef(qaId: Long, dataId: Long): Boolean

    //endregion


    //region QAWithSubTopicCrossRef
    @Transaction
    suspend fun initQAWithSubTopicCrossRef(qaId: Long, dataId: Long) {
        if (!isExistsQAWithSubTopicCrossRef(qaId, dataId))
            insert(QAWithSubTopicCrossRef(qaId, dataId))

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qaCross: QAWithSubTopicCrossRef): Long

    @Query("SELECT EXISTS(SELECT * FROM cross_ref_qa_subtopic WHERE dataId LIKE :dataId AND qaId LIKE :qaId )")
    suspend fun isExistsQAWithSubTopicCrossRef(qaId: Long, dataId: Long): Boolean


    //endregion
}