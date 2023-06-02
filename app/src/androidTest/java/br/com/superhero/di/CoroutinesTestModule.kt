package br.com.superhero.di

import br.com.core.usecase.base.CoroutinesDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

/**
 * Created by João Bosco on 29/05/2023.
 */

@OptIn(ExperimentalCoroutinesApi::class)
class AppCoroutinesTestModule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())
) : CoroutinesDispatchers {
    override fun default(): CoroutineDispatcher = testDispatcher
    override fun io(): CoroutineDispatcher = testDispatcher
    override fun main(): CoroutineDispatcher = testDispatcher
    override fun unconfined(): CoroutineDispatcher = testDispatcher
}

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesTestModule {

    @Provides
    fun provideTestDispatchers(): CoroutinesDispatchers = AppCoroutinesTestModule()
}