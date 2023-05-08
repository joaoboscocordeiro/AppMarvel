package br.com.core.data.repository

import androidx.paging.PagingSource
import br.com.core.domain.model.Character

/**
 * Created by João Bosco on 08/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
}