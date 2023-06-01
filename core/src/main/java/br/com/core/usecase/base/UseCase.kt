package br.com.core.usecase.base

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by João Bosco on 12/09/2022.
 */

abstract class UseCase<in P, R> {

    operator fun invoke(params: P): Flow<ResultStatus<R>> = flow {
        emit(ResultStatus.Loanding)
        emit(doWork(params))
    }.catch { throwable -> emit(ResultStatus.Error(throwable)) }

    protected abstract suspend fun doWork(params: P): ResultStatus<R>
}

abstract class PagingUseCase<in P, R : Any> {

    operator fun invoke(params: P): Flow<PagingData<R>> = createFlowObservable(params)

    protected abstract fun createFlowObservable(params: P): Flow<PagingData<R>>
}

abstract class FlowUseCase<in P, R : Any> {

    suspend operator fun invoke(params: P): Flow<R> = createFlowObservable(params)

    protected abstract suspend fun createFlowObservable(params: P): Flow<R>
}