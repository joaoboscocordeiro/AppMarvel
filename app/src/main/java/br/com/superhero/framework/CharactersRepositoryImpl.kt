package br.com.superhero.framework

import androidx.paging.PagingSource
import br.com.core.data.repository.CharactersRemoteDataSource
import br.com.core.data.repository.CharactersRepository
import br.com.core.domain.model.Character
import br.com.superhero.framework.network.response.DataWrapperResponse
import javax.inject.Inject

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        
    }
}