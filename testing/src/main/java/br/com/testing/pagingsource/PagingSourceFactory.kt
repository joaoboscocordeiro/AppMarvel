package br.com.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.core.domain.model.Character

/**
 * Created by João Bosco on 04/04/2023.
 */
class PagingSourceFactory {

    fun create(heroes: List<Character>) = object : PagingSource<Int, Character>() {
        override fun getRefreshKey(state: PagingState<Int, Character>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
            return LoadResult.Page(
                data = heroes,
                prevKey = null,
                nextKey = 20
            )
        }
    }
}