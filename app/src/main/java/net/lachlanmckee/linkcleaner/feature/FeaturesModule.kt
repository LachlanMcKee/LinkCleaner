package net.lachlanmckee.linkcleaner.feature

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.lachlanmckee.linkcleaner.feature.home.HomeModule
import net.lachlanmckee.linkcleaner.feature.settings.SettingsModule

@Module(
    includes = [
        HomeModule::class,
        SettingsModule::class
    ]
)
interface FeaturesModule