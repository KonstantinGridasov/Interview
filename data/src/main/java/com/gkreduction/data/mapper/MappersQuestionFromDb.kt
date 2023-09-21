package com.gkreduction.data.mapper

import com.gkreduction.data.repository.db.entity.question.QuestionAnswerDb
import com.gkreduction.data.repository.db.entity.question.SectionWithQa
import com.gkreduction.data.repository.db.entity.question.SubtopicWithQa
import com.gkreduction.data.repository.db.entity.question.TopicWithQa
import com.gkreduction.domain.entity.QuestionAnswer

fun mapperQAWithSectionToModel(items: SectionWithQa?): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQAWithSection(it))
    }
    result.sortBy { it.position }
    return result
}

fun mapperQAWithTopicToModel(items: TopicWithQa?): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQAWithSection(it))
    }
    result.sortBy { it.position }
    return result
}

fun mapperQAWithSubTopicToModel(items: SubtopicWithQa?): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQAWithSection(it))
    }
    result.sortBy { it.position }
    return result
}


fun transformQAWithSection(item: QuestionAnswerDb) = QuestionAnswer(
    id = item.qaId,
    question = item.question,
    answer = item.answer,
    position = item.position
)







