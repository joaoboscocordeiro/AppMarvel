package br.com.superhero.framework.di

import br.com.core.data.repository.CharactersRemoteDataSource
import br.com.core.data.repository.CharactersRepository
import br.com.superhero.framework.CharactersRepositoryImpl
import br.com.superhero.framework.network.response.DataWrapperResponse
import br.com.superhero.framework.remote.RetrofitCharactersDataSource
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
interface RepositoryModule {

    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitCharactersDataSource): CharactersRemoteDataSource<DataWrapperResponse>
}