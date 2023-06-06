package br.com.core.usecase

import br.com.core.data.mapper.SortingMapper
import br.com.core.data.repository.StorageRepository
import br.com.core.usecase.base.CoroutinesDispatchers
import br.com.core.usecase.base.ResultStatus
import br.com.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by João Bosco on 10/05/2023.
 */

interface SaveCharactersSortingUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(val sortingPair: Pair<String, String>)
}

class SaveCharactersSortingUseCaseImpl @Inject constructor(
    private val storageRepository: StorageRepository,
    private val sortingMapper: SortingMapper,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<SaveCharactersSortingUseCase.Params, Unit>(), SaveCharactersSortingUseCase {

    override suspend fun doWork(params: SaveCharactersSortingUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            storageRepository.saveSorting(sortingMapper.mapFromPair(params.sortingPair))
            ResultStatus.Success(Unit)
        }
    }
}