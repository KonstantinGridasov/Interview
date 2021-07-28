package com.gkreduction.interview.di.module

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gkreduction.interview.ui.main.list.ListFragment
import com.gkreduction.interview.ui.main.answer.AnswerFragment
import com.gkreduction.interview.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun contributeListFragment(): ListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeAnswerFragment(): AnswerFragment


    companion object {
        @Provides
        fun provideViewModelFactory(
            app: Application
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(MainViewModel::class.java) ->
                            MainViewModel(
                                app
                            ) as T
                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }

}