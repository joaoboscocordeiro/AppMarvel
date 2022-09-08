package br.com.core.data.repository

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface CharactersRemoteDataSource<T> {
    suspend fun fetchCharacters(queries: Map<String, String>): T
}