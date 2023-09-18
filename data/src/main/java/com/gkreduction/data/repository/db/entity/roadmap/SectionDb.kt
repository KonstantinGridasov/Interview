package com.gkreduction.data.repository.db.entity.roadmap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section_db")
class SectionDb(
    @PrimaryKey val sectionId: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val roadmapParentId: Long,
)