package br.com.superhero.presentation.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.core.usecase.GetFavoriteUseCase
import br.com.testing.MainCoroutineRule
import br.com.testing.model.CharacterFactory
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by João Bosco on 01/06/2023.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getFavoriteUseCase: GetFavoriteUseCase

    @Mock
    private lateinit var favoritesUiStateObserver: Observer<Any>

    private var character = listOf(
        CharacterFactory().create(CharacterFactory.Hero.ThreeDMan),
        CharacterFactory().create(CharacterFactory.Hero.ABomb)
    )

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(
            getFavoriteUseCase,
            mainCoroutineRule.testDispatcherProvider
        ).apply {
            state.observeForever(favoritesUiStateObserver)
        }
    }

    @Test
    fun `should notify uiState with Success from UiState when favorites ShowFavorites`() =
        runTest {
            // Arrange
            whenever(getFavoriteUseCase.invoke(Unit))
                .thenReturn(
                    flowOf(
                        character
                    )
                )

            // Action
            favoriteViewModel.getAll()

            // Assert
            verify(favoritesUiStateObserver).onChanged(isA())
        }

    @Test
    fun `should notify uiState with ShowEmpty from UiState when get favorites returns empty list`() {
        runTest {
            // Arrange
            whenever(getFavoriteUseCase.invoke())
                .thenReturn(
                    flowOf(listOf())
                )

            // Action
            favoriteViewModel.getAll()

            // Assert
            verify(favoritesUiStateObserver).onChanged(isA())
        }
    }
}