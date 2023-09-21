package com.gkreduction.roadmap.di.module

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.gkreduction.data.repository.db.DbRepositoryImpl
import com.gkreduction.domain.usecase.*
import com.gkreduction.roadmap.di.scope.MainScope
import com.gkreduction.roadmap.ui.main.MainViewModel
import com.gkreduction.roadmap.ui.main.fragment.answer.AnswerFragment
import com.gkreduction.roadmap.ui.main.fragment.answer.AnswerViewModel
import com.gkreduction.roadmap.ui.main.fragment.category.CategoryFragment
import com.gkreduction.roadmap.ui.main.fragment.category.CategoryViewModel
import com.gkreduction.roadmap.ui.main.fragment.exam.ExamFragment
import com.gkreduction.roadmap.ui.main.fragment.exam.ExamViewModel
import com.gkreduction.roadmap.ui.main.fragment.home.HomeFragment
import com.gkreduction.roadmap.ui.main.fragment.home.HomeViewModel
import com.gkreduction.roadmap.ui.main.fragment.list.ListQuestionFragment
import com.gkreduction.roadmap.ui.main.fragment.list.ListQuestionViewModel
import com.gkreduction.roadmap.ui.main.fragment.question.QuestionFragment
import com.gkreduction.roadmap.ui.main.fragment.question.QuestionViewModel
import com.gkreduction.roadmap.ui.main.fragment.roadmap.RoadmapFragment
import com.gkreduction.roadmap.ui.main.fragment.roadmap.RoadmapViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    internal abstract fun contributeQuestionFragment(): QuestionFragment

    @ContributesAndroidInjector
    internal abstract fun contributeAnswerFragment(): AnswerFragment

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeExamFragment(): ExamFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRoadmapFragment(): RoadmapFragment

    @ContributesAndroidInjector
    internal abstract fun contributeListQuestionFragment(): ListQuestionFragment


    companion object {

        @Provides
        @MainScope
        fun providesGetRoadmapsUseCase(service: DbRepositoryImpl) = GetRoadmapsUseCase(service)

        @Provides
        @MainScope
        fun providesUpdateRoadmapsUseCase(service: DbRepositoryImpl) =
            UpdateRoadmapsUseCase(service)

        @Provides
        @MainScope
        fun providesUpdateQaUseCase(service: DbRepositoryImpl) = UpdateQaUseCase(service)

        @Provides
        @MainScope
        fun providesGetRoadmapByIdUseCase(service: DbRepositoryImpl) =
            GetRoadmapByIdUseCase(service)

        @Provides
        @MainScope
        fun providesGetListQuestionByItem(service: DbRepositoryImpl) =
            GetListQuestionByItem(service)

        @Provides
        @MainScope
        fun providesGetQuestionById(service: DbRepositoryImpl) = GetQuestionById(service)


        @Provides
        fun provideViewModelFactory(
            app: Application,
            updateRoadmapsUseCase: UpdateRoadmapsUseCase,
            updateQaUseCase: UpdateQaUseCase,
            getRoadmapsUseCase: GetRoadmapsUseCase,
            getRoadmapByIdUseCase: GetRoadmapByIdUseCase,
            getListQuestionByItem: GetListQuestionByItem,
            getQuestionById: GetQuestionById
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    return when {
                        modelClass.isAssignableFrom(ExamViewModel::class.java) ->
                            ExamViewModel(
                                app
                            ) as T
                        modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                            HomeViewModel(
                                app,
                                updateRoadmapsUseCase, updateQaUseCase, getRoadmapsUseCase
                            ) as T

                        modelClass.isAssignableFrom(MainViewModel::class.java) ->
                            MainViewModel(
                                app
                            ) as T

                        modelClass.isAssignableFrom(CategoryViewModel::class.java) ->
                            CategoryViewModel(
                                app
                            ) as T

                        modelClass.isAssignableFrom(QuestionViewModel::class.java) ->
                            QuestionViewModel(
                                app
                            ) as T
                        modelClass.isAssignableFrom(AnswerViewModel::class.java) ->
                            AnswerViewModel(
                                app, getQuestionById
                            ) as T

                        modelClass.isAssignableFrom(RoadmapViewModel::class.java) ->
                            RoadmapViewModel(
                                app, getRoadmapByIdUseCase
                            ) as T

                        modelClass.isAssignableFrom(ListQuestionViewModel::class.java) ->
                            ListQuestionViewModel(
                                app, getListQuestionByItem
                            ) as T


                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }

}