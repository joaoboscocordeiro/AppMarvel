package br.com.core.data.repository

import androidx.paging.PagingSource
import br.com.core.domain.model.Character
import br.com.core.domain.model.Comic
import br.com.core.domain.model.Event

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
    suspend fun getComics(characterId: Int): List<Comic>
    suspend fun getEvents(characterId: Int): List<Event>
}