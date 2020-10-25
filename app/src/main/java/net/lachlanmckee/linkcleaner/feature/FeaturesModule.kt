package net.lachlanmckee.linkcleaner.feature

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.lachlanmckee.linkcleaner.feature.home.HomeModule
import net.lachlanmckee.linkcleaner.feature.settings.SettingsModule

@Module(
  includes = [
    HomeModule::class,
    SettingsModule::class
  ]
)
@InstallIn(ApplicationComponent::class)
interface FeaturesModule
