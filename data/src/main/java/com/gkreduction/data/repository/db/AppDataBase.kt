package com.gkreduction.data.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gkreduction.data.repository.db.dao.QuestionAnswerDao
import com.gkreduction.data.repository.db.dao.RoadmapDao
import com.gkreduction.data.repository.db.entity.question.*
import com.gkreduction.data.repository.db.entity.roadmap.*

@Database(
    entities = [
        RoadmapDb::class, SectionDb::class, TopicDb::class, SubTopicDb::class,
        QuestionAnswerDb::class, QADataRoadmapDb::class, QADataSectionDb::class,
        QADataTopicDb::class, QADataSubtopicDb::class,
        QAWithRoadmapCrossRef::class, QAWithSectionCrossRef::class,
        QAWithTopicCrossRef::class, QAWithSubTopicCrossRef::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun roadmapDao(): RoadmapDao
    abstract fun questionAnswerDao(): QuestionAnswerDao

    companion object {

        private const val DB_NAME = "chats-db"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, DB_NAME
            ).build()
        }
    }

}