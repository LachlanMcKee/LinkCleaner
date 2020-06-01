package net.lachlanmckee.linkcleaner.feature.settings

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.lachlanmckee.linkcleaner.feature.settings.view.SettingsFragment

@Module
interface SettingsModule {
    @ContributesAndroidInjector
    fun fragment(): SettingsFragment
}
