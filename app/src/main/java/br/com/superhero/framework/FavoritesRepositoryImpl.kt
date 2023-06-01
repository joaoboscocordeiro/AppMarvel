package br.com.superhero.framework

import br.com.core.data.repository.FavoritesLocalDataSource
import br.com.core.data.repository.FavoritesRepository
import br.com.core.domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by João Bosco on 10/05/2023.
 */

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesLocalDataSource: FavoritesLocalDataSource
) : FavoritesRepository {
    override fun getAll(): Flow<List<Character>> {
        return favoritesLocalDataSource.getAll()
    }

    override suspend fun isFavorite(characterId: Int): Boolean {
        return favoritesLocalDataSource.isFavorite(characterId)
    }

    override suspend fun saveFavorite(character: Character) {
        favoritesLocalDataSource.save(character)
    }

    override suspend fun deleteFavorite(character: Character) {
        favoritesLocalDataSource.delete(character)
    }
}