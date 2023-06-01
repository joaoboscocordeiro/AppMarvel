package br.com.core.usecase

import br.com.core.data.repository.CharactersRepository
import br.com.core.domain.model.Comic
import br.com.core.domain.model.Event
import br.com.core.usecase.base.CoroutinesDispatchers
import br.com.core.usecase.base.ResultStatus
import br.com.core.usecase.base.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by João Bosco on 24/04/2023.
 */
interface GetCharacterCategoriesUseCase {
    operator fun invoke(params: GetCategoriesParams): Flow<ResultStatus<Pair<List<Comic>, List<Event>>>>
    data class GetCategoriesParams(val characterId: Int)
}

class GetCharacterCategoriesUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository,
    private val dispatchers: CoroutinesDispatchers
) : GetCharacterCategoriesUseCase,
    UseCase<GetCharacterCategoriesUseCase.GetCategoriesParams, Pair<List<Comic>, List<Event>>>() {
    override suspend fun doWork(
        params: GetCharacterCategoriesUseCase.GetCategoriesParams
    ): ResultStatus<Pair<List<Comic>, List<Event>>> {
        return withContext(dispatchers.io()) {
            val comicsDeferred = async { repository.getComics(params.characterId) }
            val eventsDeferred = async { repository.getEvents(params.characterId) }

            val comics = comicsDeferred.await()
            val events = eventsDeferred.await()

            ResultStatus.Success(comics to events)
        }
    }
}