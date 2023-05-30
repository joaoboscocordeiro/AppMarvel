package br.com.core.data.repository

import br.com.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

/**
 * Created by João Bosco on 10/05/2023.
 */

interface FavoritesLocalDataSource {
    fun getAll(): Flow<List<Character>>
    suspend fun save(character: Character)
    suspend fun delete(character: Character)
}