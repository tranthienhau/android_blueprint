package ago.droid.blueprint.core.di

import ago.droid.blueprint.domain.repositories.ComponentRepository
import ago.droid.blueprint.domain.repositories.DCardRepository
import ago.droid.blueprint.domain.usecases.FetchComponentsUseCase
import ago.droid.blueprint.domain.usecases.FetchDCardsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideFetchDCardUseCase(dCardRepository: DCardRepository) : FetchDCardsUseCase = FetchDCardsUseCase(dCardRepository)

    @Provides
    @Singleton
    fun provideFetchComponentUseCase(componentRepository: ComponentRepository) : FetchComponentsUseCase = FetchComponentsUseCase(componentRepository)
}