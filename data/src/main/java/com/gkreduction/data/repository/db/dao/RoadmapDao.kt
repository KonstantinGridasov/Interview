package com.gkreduction.data.repository.db.dao

import androidx.room.*
import com.gkreduction.data.entity.RoadmapRemote
import com.gkreduction.data.entity.SectionRemote
import com.gkreduction.data.entity.SubtopicRemote
import com.gkreduction.data.entity.TopicRemote
import com.gkreduction.data.mapper.transformRemoteRoadmapToDb
import com.gkreduction.data.mapper.transformRemoteSectionToDb
import com.gkreduction.data.mapper.transformRemoteSubTopicToDb
import com.gkreduction.data.mapper.transformRemoteTopicToDb
import com.gkreduction.data.repository.db.entity.roadmap.RoadmapDb
import com.gkreduction.data.repository.db.entity.roadmap.SectionDb
import com.gkreduction.data.repository.db.entity.roadmap.SubTopicDb
import com.gkreduction.data.repository.db.entity.roadmap.TopicDb

@Dao
interface RoadmapDao {
    @Transaction
    @Query("SELECT * FROM roadmap_db ")
    fun getAllRoadmaps(): List<RoadmapDb>


    @Transaction
    fun insert(roadmapList: List<RoadmapRemote>) {
        for (roadmap in roadmapList) {
            val id = initRoadmap(roadmap)
            for (section in roadmap.sections) {
                val sectionId = initSection(section, id)
                for (topic in section.topics) {
                    val topicId = initTopic(topic, sectionId)
                    for (subtopic in topic.subtopics) {
                        initSubtopic(subtopic, topicId)
                    }

                }
            }
        }


    }

    //region Roadmap
    @Transaction
    fun initRoadmap(roadmap: RoadmapRemote): Long {
        val item = transformRemoteRoadmapToDb(roadmap)
        return if (isExistRoadmap(item.roadmapId)) {
            update(item)
            item.roadmapId
        } else
            insert(item)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roadmap: RoadmapDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(roadmap: RoadmapDb)

    @Query("SELECT EXISTS(SELECT * FROM roadmap_db WHERE roadmapId = :id)")
    fun isExistRoadmap(id: Long): Boolean
    //endregion


    //region Section
    @Transaction
    fun initSection(section: SectionRemote, parent: Long): Long {
        val item = transformRemoteSectionToDb(section, parent)
        return if (isExistSection(item.sectionId)) {
            update(item)
            item.sectionId
        } else
            insert(item)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roadmap: SectionDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(roadmap: SectionDb)

    @Query("SELECT EXISTS(SELECT * FROM section_db WHERE sectionId = :id)")
    fun isExistSection(id: Long): Boolean
    //endregion


    //region Topic
    @Transaction
    fun initTopic(topic: TopicRemote, parent: Long): Long {
        val item = transformRemoteTopicToDb(topic, parent)
        return if (isExistTopic(item.topicId)) {
            update(item)
            item.topicId
        } else
            insert(item)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roadmap: TopicDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(roadmap: TopicDb)

    @Query("SELECT EXISTS(SELECT * FROM topic_db WHERE topicId = :id)")
    fun isExistTopic(id: Long): Boolean
    //endregion


    //region SubTopic
    @Transaction
    fun initSubtopic(subtopic: SubtopicRemote, parent: Long) {
        val item = transformRemoteSubTopicToDb(subtopic, parent)
        if (isExistSubTopic(item.subtopicId)) {
            update(item)
        } else
            insert(item)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roadmap: SubTopicDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(roadmap: SubTopicDb)

    @Query("SELECT EXISTS(SELECT * FROM subtopic_db WHERE subtopicId = :id)")
    fun isExistSubTopic(id: Long): Boolean
    //endregion

}

