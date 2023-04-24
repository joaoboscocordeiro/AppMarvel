package br.com.superhero.framework.remote

import br.com.core.data.repository.CharactersRemoteDataSource
import br.com.core.domain.model.CharacterPaging
import br.com.core.domain.model.Comic
import br.com.superhero.framework.network.MarvelApi
import br.com.superhero.framework.network.response.toCharacterModel
import br.com.superhero.framework.network.response.toComicModel
import javax.inject.Inject

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource {

    override suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging {
        val data = marvelApi.getCharacters(queries).data
        val characters = data.results.map {
            it.toCharacterModel()
        }
        return CharacterPaging(
            data.offset,
            data.total,
            characters
        )
    }

    override suspend fun fetchComics(characterId: Int): List<Comic> {
        return marvelApi.getComics(characterId).data.results.map {
            it.toComicModel()
        }
    }
}