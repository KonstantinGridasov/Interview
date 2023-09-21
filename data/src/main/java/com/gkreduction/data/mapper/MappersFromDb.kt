package com.gkreduction.data.mapper

import com.gkreduction.data.repository.db.entity.roadmap.*
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.entity.Section
import com.gkreduction.domain.entity.Subtopic
import com.gkreduction.domain.entity.Topic

fun mapperRoadmapsDbToCore(items: List<RoadmapDb>?): List<Roadmap> {
    val result = ArrayList<Roadmap>()
    items?.forEach {
        result.add(transformRoadmapDB(it))
    }
    return result
}


fun transformRoadmapDB(item: RoadmapDb) = Roadmap(
    id = item.roadmapId,
    name = item.name,
    section = emptyList()
)

fun transformFullRoadmapDB(item: RoadmapFullDb) = Roadmap(
    id = item.roadmapDb.roadmapId,
    name = item.roadmapDb.name,
    section = getSectionList(item.sections)
)


fun getSectionList(sections: List<SectionWithTopic>): List<Section> {
    val result = ArrayList<Section>()
    sections.forEach { result.add(transformSectionWithTopic(it)) }
    result.sortBy { it.position }
    return result
}

fun transformSectionWithTopic(item: SectionWithTopic) = Section(
    id = item.section.sectionId,
    name = item.section.name,
    position = item.section.position,
    topics = getTopicList(item.topics)
)

fun getTopicList(topics: List<TopicWithSubtopic>): List<Topic> {
    val result = ArrayList<Topic>()
    topics.forEach { result.add(transformTopicWithSubTopic(it)) }
    return result
}

fun transformTopicWithSubTopic(item: TopicWithSubtopic) = Topic(
    id = item.topic.topicId,
    name = item.topic.name,
    subtopics = getSubTopicList(item.subtopics)
)

fun getSubTopicList(subtopics: List<SubTopicDb>): List<Subtopic> {
    val result = ArrayList<Subtopic>()
    subtopics.forEach { result.add(transformSubtopics(it)) }
    return result

}

fun transformSubtopics(item: SubTopicDb) = Subtopic(
    id = item.subtopicId,
    name = item.name
)





