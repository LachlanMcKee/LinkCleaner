package net.lachlanmckee.linkcleaner.feature.home

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap
import net.lachlanmckee.linkcleaner.di.viewmodel.ViewModelKey
import net.lachlanmckee.linkcleaner.feature.home.viewmodel.HomeViewModel
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface HomeModule {
  @Singleton
  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}
