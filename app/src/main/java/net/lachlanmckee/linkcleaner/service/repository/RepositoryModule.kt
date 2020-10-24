package net.lachlanmckee.linkcleaner.service.repository

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
  @Singleton
  @Binds
  fun bindLinkRepository(impl: LinkRepositoryImpl): LinkRepository
}
