package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.Embedded
import androidx.room.Relation

data class SectionWithTopic(
    @Embedded val section: SectionDb,
    @Relation(
        parentColumn = "serverId",
        entityColumn = "sectionParentId"
    )
    val topics: List<TopicDb>

)
