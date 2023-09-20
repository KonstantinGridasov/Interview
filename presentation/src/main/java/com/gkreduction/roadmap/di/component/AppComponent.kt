package com.gkreduction.roadmap.di.component

import android.app.Application
import com.gkreduction.roadmap.Roadmap
import com.gkreduction.roadmap.di.module.ActivityModule
import com.gkreduction.roadmap.di.module.AppModule
import com.gkreduction.roadmap.di.module.DbModule
import com.gkreduction.roadmap.di.module.NetworkModule
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
interface AppComponent : AndroidInjector<Roadmap> {
    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}