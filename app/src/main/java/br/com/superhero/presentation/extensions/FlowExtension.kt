package br.com.superhero.presentation.extensions

import br.com.core.usecase.base.ResultStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by João Bosco on 11/05/2023.
 */

suspend fun <T> Flow<ResultStatus<T>>.watchStatus(
    loading: suspend () -> Unit = {},
    success: suspend (data: T) -> Unit,
    error: suspend (throwable: Throwable) -> Unit
) {
    collect { status ->
        when (status) {
            ResultStatus.Loanding -> loading.invoke()
            is ResultStatus.Success -> success.invoke(status.data)
            is ResultStatus.Error -> error.invoke(status.throwable)
        }
    }
}