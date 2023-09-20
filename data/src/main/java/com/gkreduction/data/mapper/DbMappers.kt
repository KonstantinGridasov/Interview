package com.gkreduction.data.mapper

import com.gkreduction.data.entity.*
import com.gkreduction.data.repository.db.entity.question.*
import com.gkreduction.data.repository.db.entity.roadmap.RoadmapDb
import com.gkreduction.data.repository.db.entity.roadmap.SectionDb
import com.gkreduction.data.repository.db.entity.roadmap.SubTopicDb
import com.gkreduction.data.repository.db.entity.roadmap.TopicDb

fun transformRemoteRoadmapToDb(roadmap: RoadmapRemote) = RoadmapDb(
    roadmap.id ?: 1L,
    roadmap.name ?: ""
)

fun transformRemoteSectionToDb(section: SectionRemote, parent: Long) = SectionDb(
    sectionId = section.id ?: 1L,
    name = section.name ?: "",
    roadmapParentId = parent,
    position = section.position ?: 0
)

fun transformRemoteTopicToDb(topic: TopicRemote, parent: Long) = TopicDb(
    topicId = topic.id ?: 1L,
    name = topic.name ?: "",
    sectionParentId = parent
)

fun transformRemoteSubTopicToDb(subtopic: SubtopicRemote, parent: Long) = SubTopicDb(
    subtopicId = subtopic.id ?: 1L,
    name = subtopic.name ?: "",
    topicParentId = parent
)

fun transformRemoteQaToDb(qa: QuestionAnswerRemote) = QuestionAnswerDb(
    qa.id ?: 1L,
    qa.position ?: 0,
    qa.question ?: "",
    qa.answer ?: ""
)

fun transformQADataToRoadmapDb(qa: QADataRemote) = QADataRoadmapDb(
    qa.id.toLong(),
    qa.name
)

fun transformQADataToSectionDb(qa: QADataRemote) = QADataSectionDb(
    qa.id.toLong(),
    qa.name
)

fun transformQADataToTopicDb(qa: QADataRemote) = QADataTopicDb(
    qa.id.toLong(),
    qa.name
)


fun transformQADataToSubtopicDb(qa: QADataRemote) = QADataSubtopicDb(
    qa.id.toLong(),
    qa.name
)