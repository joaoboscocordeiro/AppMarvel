package br.com.core.usecase.base

/**
 * Created by João Bosco on 12/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
sealed class ResultStatus<out T> {

    object Loanding : ResultStatus<Nothing>()
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val throwable: Throwable) : ResultStatus<Nothing>()

    override fun toString(): String {
        return when (this) {
            Loanding -> "Loading"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[Throwable=$throwable]"
        }
    }
}
