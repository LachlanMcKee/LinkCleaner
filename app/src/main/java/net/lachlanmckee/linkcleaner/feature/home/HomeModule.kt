package net.lachlanmckee.linkcleaner.feature.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeModule {
    @ContributesAndroidInjector
    fun fragment(): HomeFragment
}
