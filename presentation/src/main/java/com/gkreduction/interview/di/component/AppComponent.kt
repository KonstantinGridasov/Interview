package com.gkreduction.interview.di.component

import android.app.Application
import com.gkreduction.interview.Interview
import com.gkreduction.interview.di.module.ActivityModule
import com.gkreduction.interview.di.module.AppModule
import com.gkreduction.interview.di.module.DbModule
import com.gkreduction.interview.di.module.NetworkModule
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
        NetworkModule::class,
        DbModule::class,
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