package br.com.core.data.repository

import br.com.core.domain.model.CharacterPaging
import br.com.core.domain.model.Comic

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface CharactersRemoteDataSource {
    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging
    suspend fun fetchComics(characterId: Int): List<Comic>
}