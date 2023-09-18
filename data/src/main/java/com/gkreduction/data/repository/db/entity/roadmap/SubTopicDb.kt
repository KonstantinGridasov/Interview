package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subtopic_db")
class SubTopicDb(
    @PrimaryKey val subtopicId: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val topicParentId: Long,

    )