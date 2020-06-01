package net.lachlanmckee.linkcleaner.feature.home

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import net.lachlanmckee.linkcleaner.di.scope.FeatureScope
import net.lachlanmckee.linkcleaner.di.viewmodel.ViewModelKey
import net.lachlanmckee.linkcleaner.feature.home.view.HomeFragment
import net.lachlanmckee.linkcleaner.feature.home.viewmodel.HomeViewModel
import javax.inject.Singleton

@Module
interface HomeModule {
    @FeatureScope
    @ContributesAndroidInjector
    fun fragment(): HomeFragment

    @Singleton
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}
