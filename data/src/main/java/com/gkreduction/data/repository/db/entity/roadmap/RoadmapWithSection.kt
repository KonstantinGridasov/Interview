package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.Embedded
import androidx.room.Relation

data class RoadmapWithSection(
    @Embedded val roadmapDb: RoadmapDb,
    @Relation(
        parentColumn = "roadmapId",
        entityColumn = "roadmapParentId"
    )
    val sections: List<SectionDb>

)
