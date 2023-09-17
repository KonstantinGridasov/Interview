package com.gkreduction.data.mapper

import android.util.Log
import com.gkreduction.data.entity.RoadmapRemote
import com.gkreduction.data.entity.SectionRemote
import com.gkreduction.data.entity.SubtopicRemote
import com.gkreduction.data.entity.TopicRemote
import com.gkreduction.domain.entity.Roadmap
import com.gkreduction.domain.entity.Section
import com.gkreduction.domain.entity.Subtopic
import com.gkreduction.domain.entity.Topic

fun mapListRoadmap(list: List<RoadmapRemote>): List<Roadmap> {
    val result = ArrayList<Roadmap>()
    list.forEach {
        result.add(it.toCoreModel())
    }
    return result
}

fun RoadmapRemote.toCoreModel(): Roadmap = Roadmap(
    id = this.id ?: 1,
    name = this.name ?: "",
    section = mapListSection(this.sections)
)

fun mapListSection(sections: List<SectionRemote>?): List<Section> {
    val result = ArrayList<Section>()
    Log.d("MAPPERS ", "sections is null = " + sections?.size)
    Log.d("MAPPERS ", "size = " + sections?.size)
    sections?.forEach {
        result.add(it.toCoreModel())
    }
    return result

}

fun SectionRemote.toCoreModel(): Section = Section(
    id = this.id ?: 1,
    name = this.name ?: "",
    position = this.position ?: 0,
    topics = mapListTopic(this.topics)
)

fun mapListTopic(sections: List<TopicRemote>?): List<Topic> {
    val result = ArrayList<Topic>()
    sections?.forEach {
        result.add(it.toCoreModel())
    }
    return result
}

fun TopicRemote.toCoreModel(): Topic = Topic(
    id = this.id ?: 1,
    name = this.name ?: "",
    subtopics = mapListSubTopic(this.subtopics)
)

fun mapListSubTopic(sections: List<SubtopicRemote>?): List<Subtopic> {
    val result = ArrayList<Subtopic>()
    sections?.forEach {
        result.add(it.toCoreModel())
    }
    return result
}

fun SubtopicRemote.toCoreModel(): Subtopic = Subtopic(
    id = this.id ?: 1,
    name = this.name ?: "",
)

