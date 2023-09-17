package com.gkreduction.interview.di.module

import com.gkreduction.interview.di.scope.MainScope
import com.gkreduction.interview.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesMainActivity(): MainActivity

}