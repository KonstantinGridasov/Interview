package com.gkreduction.interview.di.module

import android.app.Application
import com.gkreduction.data.repository.db.AppDataBase
import com.gkreduction.data.repository.db.DbRepositoryImpl
import com.gkreduction.data.repository.db.dao.QuestionAnswerDao
import com.gkreduction.data.repository.db.dao.RoadmapDao
import com.gkreduction.data.repository.network.datasource.NetworkDataStore
import com.gkreduction.domain.repository.DbRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

class DbModule {
    @Provides
    @Singleton
    fun provideDbRepository(repository: DbRepositoryImpl): DbRepository = repository

    @Provides
    @Singleton
    fun provideDbServiceImpl(
        roadmapDao: RoadmapDao,
        questionAnswerDao: QuestionAnswerDao,
        dataSource: NetworkDataStore

    ) = DbRepositoryImpl(roadmapDao, questionAnswerDao, dataSource)


    @Provides
    @Singleton
    fun provideAppDatabase(app: Application) = AppDataBase.getInstance(app.applicationContext)


    @Provides
    @Singleton
    fun provideRoadmapDao(appDatabase: AppDataBase): RoadmapDao = appDatabase.roadmapDao()

    @Provides
    @Singleton
    fun provideQuestionAnswerDao(appDatabase: AppDataBase): QuestionAnswerDao =
        appDatabase.questionAnswerDao()
}