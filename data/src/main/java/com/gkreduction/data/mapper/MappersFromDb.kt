package com.gkreduction.data.mapper

import com.gkreduction.data.repository.db.entity.roadmap.*
import com.gkreduction.domain.entity.*

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
//    section = emptyList()
)

fun transformFullRoadmapDB(item: RoadmapFullDb): List<ItemRoadmap> {
    var array = ArrayList<ItemRoadmap>()
    for (i in item.sections) {
        array.add(ItemRoadmap(i.section.name, i.section.sectionId, TypeItem.SECTION))
        for (k in i.topics) {
            array.add(ItemRoadmap(k.topic.name, k.topic.topicId, TypeItem.TOPIC))

            for (j in k.subtopics) {
                array.add(
                    ItemRoadmap(
                        j.name,
                        j.subtopicId,
                        TypeItem.SUBTOPIC
                    )
                )

            }
        }
    }
    return array

}

//fun transformFullRoadmapDB(item: RoadmapFullDb) = Roadmap(
//    id = item.roadmapDb.roadmapId,
//    name = item.roadmapDb.name,
//    section = getSectionList(item.sections)
//)








