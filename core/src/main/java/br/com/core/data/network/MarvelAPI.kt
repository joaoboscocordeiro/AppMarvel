package br.com.core.data.network

import br.com.core.data.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by João Bosco on 06/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface MarvelAPI {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse
}