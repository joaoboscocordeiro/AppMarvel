package br.com.core.usecase.base

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by João Bosco on 12/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
data class AppCoroutinesDispatchers(
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main: CoroutineDispatcher
)
