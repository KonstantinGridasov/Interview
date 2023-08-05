package com.gkreduction.interview.di.module

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.gkreduction.interview.ui.main.MainViewModel
import com.gkreduction.interview.ui.main.fragmnet.answer.AnswerFragment
import com.gkreduction.interview.ui.main.fragmnet.answer.AnswerViewModel
import com.gkreduction.interview.ui.main.fragmnet.category.CategoryFragment
import com.gkreduction.interview.ui.main.fragmnet.category.CategoryViewModel
import com.gkreduction.interview.ui.main.fragmnet.question.QuestionFragment
import com.gkreduction.interview.ui.main.fragmnet.question.QuestionViewModel
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


    companion object {
        @Provides
        fun provideViewModelFactory(
            app: Application
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    return when {
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
                                app
                            ) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }

}