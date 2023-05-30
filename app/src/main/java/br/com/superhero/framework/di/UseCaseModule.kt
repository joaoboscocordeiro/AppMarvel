package br.com.superhero.framework.di

import br.com.core.usecase.AddFavoriteUseCase
import br.com.core.usecase.AddFavoriteUseCaseImpl
import br.com.core.usecase.CheckFavoriteUseCase
import br.com.core.usecase.CheckFavoriteUseCaseImpl
import br.com.core.usecase.GetCharacterCategoriesUseCase
import br.com.core.usecase.GetCharacterCategoriesUseCaseImpl
import br.com.core.usecase.GetCharactersUseCase
import br.com.core.usecase.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by João Bosco on 27/03/2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetCharacterCategoriesUseCase(useCase: GetCharacterCategoriesUseCaseImpl):
            GetCharacterCategoriesUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase
}