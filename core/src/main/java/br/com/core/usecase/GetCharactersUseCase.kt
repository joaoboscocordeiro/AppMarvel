package br.com.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.core.data.repository.CharactersRepository
import br.com.core.domain.model.Character
import br.com.core.usecase.GetCharactersUseCase.GetCharactersParams
import br.com.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by João Bosco on 12/09/2022.
 */
interface GetCharactersUseCase {
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>
    data class GetCharactersParams(val query: String, val pagingConfig: PagingConfig)
}

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : PagingUseCase<GetCharactersParams, Character>(),
    GetCharactersUseCase {

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
        return charactersRepository.getCachedCharacters(params.query, params.pagingConfig)
    }
}