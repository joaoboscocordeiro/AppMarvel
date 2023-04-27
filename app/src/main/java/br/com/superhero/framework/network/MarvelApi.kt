package br.com.superhero.framework.network

import br.com.superhero.framework.network.response.CharacterResponse
import br.com.superhero.framework.network.response.ComicResponse
import br.com.superhero.framework.network.response.DataWrapperResponse
import br.com.superhero.framework.network.response.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by João Bosco on 06/09/2022.
 */
interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse<CharacterResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId")
        characterId: Int
    ): DataWrapperResponse<ComicResponse>

    @GET("characters/{characterId}/events")
    suspend fun getEvents(
        @Path("characterId")
        characterId: Int
    ): DataWrapperResponse<EventResponse>
}