package com.gkreduction.data.mapper

import android.util.Log
import com.gkreduction.data.repository.db.entity.roadmap.RoadmapDb
import com.gkreduction.domain.entity.Roadmap

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
    section = null
)

