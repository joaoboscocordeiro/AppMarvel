package br.com.superhero.framework.remote

import br.com.core.data.repository.CharactersRemoteDataSource
import br.com.superhero.framework.network.MarvelApi
import br.com.superhero.framework.network.response.DataWrapperResponse
import javax.inject.Inject

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return marvelApi.getCharacters(queries)
    }
}