package br.com.superhero.framework.di

import br.com.superhero.BuildConfig
import br.com.superhero.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by João Bosco on 14/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}