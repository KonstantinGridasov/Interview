package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.Embedded
import androidx.room.Relation

data class TopicWithSubtopic(
    @Embedded val section: TopicDb,
    @Relation(
        parentColumn = "topicId",
        entityColumn = "topicParentId"
    )
    val subtopics: List<SubTopicDb>

)
