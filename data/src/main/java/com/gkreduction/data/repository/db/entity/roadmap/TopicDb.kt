package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_db")
class TopicDb(
    @PrimaryKey val topicId: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val sectionParentId: Long,
)