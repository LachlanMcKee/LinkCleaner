package net.lachlanmckee.linkcleaner.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import net.lachlanmckee.linkcleaner.LinkCleanerApplication
import net.lachlanmckee.linkcleaner.feature.FeaturesModule
import net.lachlanmckee.linkcleaner.service.repository.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    RepositoryModule::class,
    FeaturesModule::class
  ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
  fun inject(linkCleanerApplication: LinkCleanerApplication)
}
