package com.gkreduction.data.mapper

import com.gkreduction.data.repository.db.entity.question.*
import com.gkreduction.data.utils.randomWithSize
import com.gkreduction.domain.entity.QuestionAnswer


fun mapperQAWithRoadmapToModel(
    items: RoadmapWithQa?,
    random: Boolean,
    size: Int
): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaList?.forEach {
        result.add(transformQADbToModel(it))
    }
    return randomWithSize(result, random, size)
}


fun mapperQAWithSectionToModel(
    items: SectionWithQa?,
    random: Boolean,
    size: Int
): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQADbToModel(it))
    }
    return randomWithSize(result, random, size)
}

fun mapperQAWithTopicToModel(
    items: TopicWithQa?,
    random: Boolean,
    size: Int
): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQADbToModel(it))
    }
    return randomWithSize(result, random, size)

}

fun mapperQAWithSubTopicToModel(
    items: SubtopicWithQa?,
    random: Boolean,
    size: Int
): List<QuestionAnswer> {
    val result = ArrayList<QuestionAnswer>()
    items?.qaRoadmaps?.forEach {
        result.add(transformQADbToModel(it))
    }
    return randomWithSize(result, random, size)

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







