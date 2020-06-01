package net.lachlanmckee.linkcleaner

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.lachlanmckee.linkcleaner.di.AndroidModule
import net.lachlanmckee.linkcleaner.di.DaggerAppComponent
import javax.inject.Inject

class LinkCleanerApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .androidModule(AndroidModule(this))
            .build()
            .inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
