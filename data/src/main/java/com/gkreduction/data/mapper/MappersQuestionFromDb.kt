package com.gkreduction.data.mapper

import com.gkreduction.data.repository.db.entity.question.QuestionAnswerDb
import com.gkreduction.data.repository.db.entity.question.SectionWithQa
import com.gkreduction.data.repository.db.entity.question.SubtopicWithQa
import com.gkreduction.data.repository.db.entity.question.TopicWithQa
import com.gkreduction.domain.entity.QuestionAnswer

fun mapperQAWithSectionToModel(items: SectionWithQa?): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQADbToModel(it))
    }
    result.sortBy { it.position }
    return result
}

fun mapperQAWithTopicToModel(items: TopicWithQa?): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQADbToModel(it))
    }
    result.sortBy { it.position }
    return result
}

fun mapperQAWithSubTopicToModel(items: SubtopicWithQa?): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQADbToModel(it))
    }
    result.sortBy { it.position }
    return result
}

fun transformQAListDbToModel(list: List<QuestionAnswerDb>?): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    list?.forEach {
        result.add(transformQADbToModel(it))
    }
    return result
}


fun transformQADbToModel(item: QuestionAnswerDb) = QuestionAnswer(
    id = item.qaId,
    question = item.question,
    answer = item.answer,
    position = item.position
)







