package com.gkreduction.roadmap.di.module

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.gkreduction.data.repository.db.DbRepositoryImpl
import com.gkreduction.domain.usecase.*
import com.gkreduction.roadmap.di.scope.MainScope
import com.gkreduction.roadmap.ui.dialog.finish.FinishDialog
import com.gkreduction.roadmap.ui.dialog.finish.FinishViewModel
import com.gkreduction.roadmap.ui.dialog.help.HelpDialog
import com.gkreduction.roadmap.ui.dialog.help.HelpDialogViewModel
import com.gkreduction.roadmap.ui.main.MainViewModel
import com.gkreduction.roadmap.ui.main.fragment.answer.AnswerFragment
import com.gkreduction.roadmap.ui.main.fragment.answer.AnswerViewModel
import com.gkreduction.roadmap.ui.main.fragment.exam.ExamFragment
import com.gkreduction.roadmap.ui.main.fragment.exam.ExamViewModel
import com.gkreduction.roadmap.ui.main.fragment.home.HomeFragment
import com.gkreduction.roadmap.ui.main.fragment.home.HomeViewModel
import com.gkreduction.roadmap.ui.main.fragment.question.QuestionsFragment
import com.gkreduction.roadmap.ui.main.fragment.question.QuestionsViewModel
import com.gkreduction.roadmap.ui.main.fragment.roadmap.RoadmapFragment
import com.gkreduction.roadmap.ui.main.fragment.roadmap.RoadmapViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    
    @ContributesAndroidInjector
    internal abstract fun contributeAnswerFragment(): AnswerFragment

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeExamFragment(): ExamFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRoadmapFragment(): RoadmapFragment

    @ContributesAndroidInjector
    internal abstract fun contributeQuestionsFragment(): QuestionsFragment

    @ContributesAndroidInjector
    abstract fun contributeHelpDialog(): HelpDialog

    @ContributesAndroidInjector
    abstract fun contributeFinishDialog(): FinishDialog

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
        fun providesGetListTheoryByItem(service: DbRepositoryImpl) =
            GetListQuestionsByItem(service)

        @Provides
        @MainScope
        fun providesGetQuestionById(service: DbRepositoryImpl) = GetQuestionById(service)


        @Provides
        @MainScope
        fun providesGetRandomQuestion(service: DbRepositoryImpl) = GetRandomQuestion(service)


        @Provides
        fun provideViewModelFactory(
            app: Application,
            updateRoadmapsUseCase: UpdateRoadmapsUseCase,
            updateQaUseCase: UpdateQaUseCase,
            getRoadmapsUseCase: GetRoadmapsUseCase,
            getRoadmapByIdUseCase: GetRoadmapByIdUseCase,
            getListQuestionByItem: GetListQuestionsByItem,
            getQuestionById: GetQuestionById,
            getRandomQuestion: GetRandomQuestion
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
                                app, getRandomQuestion, getQuestionById
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

                        modelClass.isAssignableFrom(AnswerViewModel::class.java) ->
                            AnswerViewModel(
                                app, getQuestionById
                            ) as T

                        modelClass.isAssignableFrom(RoadmapViewModel::class.java) ->
                            RoadmapViewModel(
                                app, getRoadmapByIdUseCase
                            ) as T

                        modelClass.isAssignableFrom(QuestionsViewModel::class.java) ->
                            QuestionsViewModel(
                                app, getListQuestionByItem
                            ) as T

                        modelClass.isAssignableFrom(HelpDialogViewModel::class.java) ->
                            HelpDialogViewModel(
                                app,
                            ) as T

                        modelClass.isAssignableFrom(FinishViewModel::class.java) ->
                            FinishViewModel(
                                app,
                            ) as T


                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }

}