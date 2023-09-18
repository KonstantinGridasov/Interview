package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roadmap_db")
class RoadmapDb(
    @PrimaryKey val roadmapId: Long,
    @ColumnInfo val name: String
)