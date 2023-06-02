package br.com.core.usecase

import br.com.core.data.repository.FavoritesRepository
import br.com.core.domain.model.Character
import br.com.core.usecase.base.CoroutinesDispatchers
import br.com.core.usecase.base.ResultStatus
import br.com.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by João Bosco on 10/05/2023.
 */

interface AddFavoriteUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(val characterId: Int, val name: String, val imageUrl: String)
}

class AddFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<AddFavoriteUseCase.Params, Unit>(), AddFavoriteUseCase {

    override suspend fun doWork(params: AddFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            favoriteRepository.saveFavorite(
                Character(params.characterId, params.name, params.imageUrl)
            )
            ResultStatus.Success(Unit)
        }
    }
}