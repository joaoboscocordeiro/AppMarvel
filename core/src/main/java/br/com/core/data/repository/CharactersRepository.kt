package br.com.core.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.core.domain.model.Character
import br.com.core.domain.model.Comic
import br.com.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
    fun getCachedCharacters(
        query: String,
        orderBy: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Character>>

    suspend fun getComics(characterId: Int): List<Comic>
    suspend fun getEvents(characterId: Int): List<Event>
}