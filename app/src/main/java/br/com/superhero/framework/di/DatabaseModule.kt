package br.com.superhero.framework.di

import android.content.Context
import androidx.room.Room
import br.com.core.data.DbConstants
import br.com.superhero.framework.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by João Bosco on 09/05/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        DbConstants.APP_DATABASE_NAME
    ).build()

    @Provides
    fun provideFavoriteDao(appDataBase: AppDataBase) = appDataBase.favoriteDao()

    @Provides
    fun provideCharacterDao(appDataBase: AppDataBase) = appDataBase.characterDao()

    @Provides
    fun provideRemoteKeyDao(appDataBase: AppDataBase) = appDataBase.remoteKeyDao()
}