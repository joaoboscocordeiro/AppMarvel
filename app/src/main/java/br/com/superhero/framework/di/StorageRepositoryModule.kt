package br.com.superhero.framework.di

import br.com.core.data.repository.StorageLocalDataSource
import br.com.core.data.repository.StorageRepository
import br.com.superhero.framework.StorageRepositoryImpl
import br.com.superhero.framework.local.DataStorePreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by João Bosco on 06/06/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
interface StorageRepositoryModule {

    @Binds
    fun bindStorageRepository(repository: StorageRepositoryImpl): StorageRepository

    @Singleton
    @Binds
    fun bindLocalDataSource(dataSource: DataStorePreferencesDataSource): StorageLocalDataSource
}