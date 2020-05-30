package net.lachlanmckee.linkcleaner.feature.settings

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SettingsModule {
    @ContributesAndroidInjector
    fun fragment(): SettingsFragment
}
