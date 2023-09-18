package com.gkreduction.data.mapper

import com.gkreduction.data.entity.*
import com.gkreduction.domain.entity.*

fun mapperRemoteListToModelList(items: List<BaseRemote>?): List<BaseElement> {
    val result = ArrayList<BaseElement>()
    items?.forEach {
        when (it) {
            is RoadmapRemote -> result.add(transformRoadmapRemote(it))
            is SectionRemote -> result.add(transformSectionRemote(it))
            is TopicRemote -> result.add(transformTopicRemote(it))
            is SubtopicRemote -> result.add(transformSubtopicRemote(it))
        }
    }
    return result
}


fun transformRoadmapRemote(item: RoadmapRemote) = Roadmap(
    id = item.id ?: 1,
    name = item.name ?: "",
    section = mapperRemoteListToModelList(item.sections)
)

fun transformSectionRemote(item: SectionRemote) = Section(
    id = item.id ?: 1,
    name = item.name ?: "",
    position = item.position ?: 0,
    topics = mapperRemoteListToModelList(item.topics)
)

fun transformTopicRemote(item: TopicRemote) = Topic(
    id = item.id ?: 1,
    name = item.name ?: "",
    subtopics = mapperRemoteListToModelList(item.subtopics)
)

fun transformSubtopicRemote(item: SubtopicRemote) = Subtopic(
    id = item.id ?: 1,
    name = item.name ?: ""
)
