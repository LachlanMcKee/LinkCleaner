package net.lachlanmckee.linkcleaner.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {
    @Singleton
    @Provides
    fun bindApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun bindContext(): Context {
        return application
    }
}
