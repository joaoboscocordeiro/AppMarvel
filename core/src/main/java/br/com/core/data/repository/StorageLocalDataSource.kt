package br.com.core.data.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by João Bosco on 06/06/2023.
 */
interface StorageLocalDataSource {
    val sorting: Flow<String>
    suspend fun saveSorting(sorting: String)
}