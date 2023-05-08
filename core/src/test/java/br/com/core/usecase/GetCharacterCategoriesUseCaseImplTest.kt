package br.com.core.usecase

import br.com.core.data.repository.CharactersRepository
import br.com.core.usecase.base.ResultStatus
import br.com.testing.MainCoroutineRule
import br.com.testing.model.CharacterFactory
import br.com.testing.model.ComicFactory
import br.com.testing.model.EventFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by João Bosco on 08/05/2023.
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterCategoriesUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CharactersRepository

    private val character = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
    private val comics = listOf(ComicFactory().create(ComicFactory.FakeComic.FComic))
    private val events = listOf(EventFactory().create(EventFactory.FakeEvent.FEvent))

    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase

    @Before
    fun seTup() {
        getCharacterCategoriesUseCase = GetCharacterCategoriesUseCaseImpl(
            repository,
            mainCoroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() =
        runTest {
            // Arrange
            whenever(repository.getComics(character.id)).thenReturn(comics)
            whenever(repository.getEvents(character.id)).thenReturn(events)

            // Act
            val result = getCharacterCategoriesUseCase
                .invoke(GetCharacterCategoriesUseCase.GetCategoriesParams(character.id))

            // Assert
            val resultList = result.toList()
            assertEquals(ResultStatus.Loanding, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Success)
        }

    @Test
    fun `should return Error from ResultStatus when get comics request returns error`() =
        runTest {
            // Arrange
            whenever(repository.getComics(character.id)).thenAnswer { throw Throwable() }

            // Act
            val result = getCharacterCategoriesUseCase
                .invoke(GetCharacterCategoriesUseCase.GetCategoriesParams(character.id))

            // Assert
            val resultList = result.toList()
            assertEquals(ResultStatus.Loanding, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Error)
        }

    @Test
    fun `should return Error from ResultStatus when get events request returns error`() =
        runTest {
            // Arrange
            whenever(repository.getComics(character.id)).thenReturn(comics)
            whenever(repository.getEvents(character.id)).thenAnswer { throw Throwable() }

            // Act
            val result = getCharacterCategoriesUseCase
                .invoke(GetCharacterCategoriesUseCase.GetCategoriesParams(character.id))

            // Assert
            val resultList = result.toList()
            assertEquals(ResultStatus.Loanding, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Error)
        }
}