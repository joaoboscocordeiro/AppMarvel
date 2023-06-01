package br.com.core.usecase

import br.com.core.data.repository.FavoritesRepository
import br.com.core.usecase.base.CoroutinesDispatchers
import br.com.core.usecase.base.ResultStatus
import br.com.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by João Bosco on 29/05/2023.
 */

interface CheckFavoriteUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Boolean>>
    data class Params(val characterId: Int)
}

class CheckFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<CheckFavoriteUseCase.Params, Boolean>(), CheckFavoriteUseCase {

    override suspend fun doWork(params: CheckFavoriteUseCase.Params): ResultStatus<Boolean> {
        return withContext(dispatchers.io()) {
            val isFavorite = favoritesRepository.isFavorite(params.characterId)
            ResultStatus.Success(isFavorite)
        }
    }
}