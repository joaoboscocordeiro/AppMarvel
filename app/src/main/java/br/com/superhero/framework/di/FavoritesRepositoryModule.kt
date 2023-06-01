package br.com.superhero.framework.di

import br.com.core.data.repository.FavoritesLocalDataSource
import br.com.core.data.repository.FavoritesRepository
import br.com.superhero.framework.FavoritesRepositoryImpl
import br.com.superhero.framework.local.RoomFavoritesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by João Bosco on 09/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {

    @Binds
    fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindLocalDataSource(dataSource: RoomFavoritesDataSource): FavoritesLocalDataSource

}