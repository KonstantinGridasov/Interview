package com.gkreduction.interview.di.module

import com.gkreduction.interview.ui.main.MainActivity
import com.gkreduction.tradeconsolegames.di.scope.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesHomeActivity(): MainActivity


}