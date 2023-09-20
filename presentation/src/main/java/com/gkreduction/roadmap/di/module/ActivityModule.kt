package com.gkreduction.roadmap.di.module

import com.gkreduction.roadmap.di.scope.MainScope
import com.gkreduction.roadmap.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesMainActivity(): MainActivity

}