package br.com.core.data.repository

import br.com.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

/**
 * Created by João Bosco on 10/05/2023.
 */

interface FavoritesRepository {
    fun getAll(): Flow<List<Character>>
    suspend fun isFavorite(characterId: Int): Boolean
    suspend fun saveFavorite(character: Character)
    suspend fun deleteFavorite(character: Character)
}