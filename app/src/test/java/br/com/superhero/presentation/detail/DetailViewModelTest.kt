package br.com.superhero.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.core.domain.model.Comic
import br.com.core.usecase.GetCharacterCategoriesUseCase
import br.com.core.usecase.base.ResultStatus
import br.com.superhero.R
import br.com.testing.MainCoroutineRule
import br.com.testing.model.CharacterFactory
import br.com.testing.model.ComicFactory
import br.com.testing.model.EventFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by João Bosco on 28/04/2023.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase

    @Mock
    private lateinit var uiStateObserver: Observer<DetailViewModel.UiState>

    private var character = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
    private var comics = listOf(ComicFactory().create(ComicFactory.FakeComic.FComic))
    private var events = listOf(EventFactory().create(EventFactory.FakeEvent.FEvent))

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(getCharacterCategoriesUseCase)
        detailViewModel.uiState.observeForever(uiStateObserver)
    }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns success`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            comics to events
                        )
                    )
                )

            // Act
            detailViewModel.getCharacterCategories(character.id)

            // Assert
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as DetailViewModel.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(2, categoriesParentList.size)
            assertEquals(
                R.string.details_comics_category,
                categoriesParentList[0].categoryStringResId
            )
            assertEquals(
                R.string.details_events_category,
                categoriesParentList[1].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only comics`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            comics to emptyList()
                        )
                    )
                )

            // Act
            detailViewModel.getCharacterCategories(character.id)

            // Assert
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as DetailViewModel.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(1, categoriesParentList.size)
            assertEquals(
                R.string.details_comics_category,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only events`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            emptyList<Comic>() to  events
                        )
                    )
                )

            // Act
            detailViewModel.getCharacterCategories(character.id)

            // Assert
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Success>())

            val uiStateSuccess = detailViewModel.uiState.value as DetailViewModel.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(1, categoriesParentList.size)
            assertEquals(
                R.string.details_events_category,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Empty from UiState when get character categories returns an empty result list`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            emptyList<Comic>() to  emptyList()
                        )
                    )
                )

            // Act
            detailViewModel.getCharacterCategories(character.id)

            // Assert
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Empty>())
        }

    @Test
    fun `should notify uiState with Error from UiState when get character categories returns an exception`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Error(
                            Throwable()
                        )
                    )
                )

            // Act
            detailViewModel.getCharacterCategories(character.id)

            // Assert
            verify(uiStateObserver).onChanged(isA<DetailViewModel.UiState.Error>())
        }
}