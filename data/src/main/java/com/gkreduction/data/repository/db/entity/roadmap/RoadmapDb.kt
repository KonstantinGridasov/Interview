package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.*

@Entity(tableName = "roadmap_db")
class RoadmapDb(
    @PrimaryKey val roadmapId: Long,
    @ColumnInfo val name: String
)


@Entity(tableName = "section_db")
class SectionDb(
    @PrimaryKey val sectionId: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val roadmapParentId: Long,
    @ColumnInfo val position: Int,
)


@Entity(tableName = "topic_db")
class TopicDb(
    @PrimaryKey val topicId: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val sectionParentId: Long,
)


@Entity(tableName = "subtopic_db")
class SubTopicDb(
    @PrimaryKey val subtopicId: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val topicParentId: Long
)


data class SectionWithTopic(
    @Embedded val section: SectionDb,
    @Relation(
        parentColumn = "serverId",
        entityColumn = "sectionParentId"
    )
    val topics: List<TopicDb>

)


data class RoadmapWithSection(
    @Embedded val roadmapDb: RoadmapDb,
    @Relation(
        parentColumn = "roadmapId",
        entityColumn = "roadmapParentId"
    )
    val sections: List<SectionDb>

)

data class TopicWithSubtopic(
    @Embedded val section: TopicDb,
    @Relation(
        parentColumn = "topicId",
        entityColumn = "topicParentId"
    )
    val subtopics: List<SubTopicDb>

)
