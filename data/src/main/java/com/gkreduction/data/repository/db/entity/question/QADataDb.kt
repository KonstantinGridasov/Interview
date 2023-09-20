package com.gkreduction.data.repository.db.entity.question

import androidx.room.*

@Entity(tableName = "qa_db")
data class QuestionAnswerDb(
    @PrimaryKey val qaId: Long,
    @ColumnInfo val position: Int,
    @ColumnInfo val question: String,
    @ColumnInfo val answer: String,
)

@Entity(tableName = "qa_data_roadmap_db")
data class QADataRoadmapDb(
    @PrimaryKey val dataId: Long,
    @ColumnInfo val name: String,
)

@Entity(tableName = "qa_data_section_db")
data class QADataSectionDb(
    @PrimaryKey val dataId: Long,
    @ColumnInfo val name: String,
)

@Entity(tableName = "qa_data_topic_db")
data class QADataTopicDb(
    @PrimaryKey val dataId: Long,
    @ColumnInfo val name: String,
)

@Entity(tableName = "qa_data_subtopic_db")
data class QADataSubtopicDb(
    @PrimaryKey val dataId: Long,
    @ColumnInfo val name: String,
)

@Entity(tableName = "cross_ref_qa_roadmap", primaryKeys = ["qaId", "dataId"])
data class QAWithRoadmapCrossRef(
    val qaId: Long,
    val dataId: Long,
)

@Entity(tableName = "cross_ref_qa_section", primaryKeys = ["qaId", "dataId"])
data class QAWithSectionCrossRef(
    val qaId: Long,
    val dataId: Long,
)

@Entity(tableName = "cross_ref_qa_topic", primaryKeys = ["qaId", "dataId"])
data class QAWithTopicCrossRef(
    val qaId: Long,
    val dataId: Long,
)

@Entity(tableName = "cross_ref_qa_subtopic", primaryKeys = ["qaId", "dataId"])
data class QAWithSubTopicCrossRef(
    val qaId: Long,
    val dataId: Long,
)



data class QAWithRoadmap(
    @Embedded val qa: QuestionAnswerDb,
    @Relation(
        parentColumn = "qaId",
        entityColumn = "dataId",
        associateBy = Junction(QAWithRoadmapCrossRef::class)

    )
    val qaRoadmaps: List<QADataRoadmapDb>
)

data class QAWithSection(
    @Embedded val qa: QuestionAnswerDb,
    @Relation(
        parentColumn = "qaId",
        entityColumn = "dataId",
        associateBy = Junction(QAWithSectionCrossRef::class)

    )
    val qaSections: List<QADataSectionDb>
)

data class QAWithTopic(
    @Embedded val qa: QuestionAnswerDb,
    @Relation(
        parentColumn = "qaId",
        entityColumn = "dataId",
        associateBy = Junction(QAWithTopicCrossRef::class)

    )
    val qaTopics: List<QADataTopicDb>
)


data class QAWithSubtopic(
    @Embedded val qa: QuestionAnswerDb,
    @Relation(
        parentColumn = "qaId",
        entityColumn = "dataId",
        associateBy = Junction(QAWithSubTopicCrossRef::class)

    )
    val qaSubtopics: List<QADataSubtopicDb>
)





