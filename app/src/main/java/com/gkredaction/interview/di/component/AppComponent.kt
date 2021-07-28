package com.gkredaction.interview.di.component

import android.app.Application
import com.gkredaction.interview.Interview
import com.gkredaction.interview.di.module.ActivityModule
import com.gkredaction.interview.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<Interview> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}