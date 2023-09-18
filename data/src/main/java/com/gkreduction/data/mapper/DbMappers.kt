package com.gkreduction.data.mapper

import com.gkreduction.data.entity.QADataRemote
import com.gkreduction.data.entity.QuestionAnswerRemote
import com.gkreduction.data.repository.db.entity.question.*

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