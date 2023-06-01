package br.com.superhero.framework.di

import br.com.superhero.framework.imageloader.GlideImageLoader
import br.com.superhero.framework.imageloader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Created by João Bosco on 20/04/2023.
 */
@Module
@InstallIn(FragmentComponent::class)
interface AppModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
}