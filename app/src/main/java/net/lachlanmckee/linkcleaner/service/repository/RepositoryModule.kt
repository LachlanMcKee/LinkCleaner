package net.lachlanmckee.linkcleaner.service.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {
  @Singleton
  @Binds
  fun bindLinkRepository(impl: LinkRepositoryImpl): LinkRepository
}
