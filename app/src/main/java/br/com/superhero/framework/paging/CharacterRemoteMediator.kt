package br.com.superhero.framework.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.com.core.data.repository.CharactersRemoteDataSource
import br.com.superhero.framework.db.AppDataBase
import br.com.superhero.framework.db.entity.CharacterEntity
import br.com.superhero.framework.db.entity.RemoteKey
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by João Bosco on 01/06/2023.
 */

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val query: String,
    private val database: AppDataBase,
    private val remoteDataSource: CharactersRemoteDataSource
) : RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = database.characterDao()
    private val remoteKeyDao = database.remoteKeyDao()

    @Suppress("ReturnCount")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKey()
                    }

                    if (remoteKey.nextOffSet == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextOffSet
                }
            }

            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val characterPaging = remoteDataSource.fetchCharacters(queries)
            val responseOffset = characterPaging.offset
            val totalCharacters = characterPaging.total

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearAll()
                    characterDao.clearAll()
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(nextOffSet = responseOffset + state.config.pageSize)
                )

                val charactersEntities = characterPaging.characters.map {
                    CharacterEntity(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.imageUrl
                    )
                }

                characterDao.insertAll(charactersEntities)
            }

            MediatorResult.Success(endOfPaginationReached = responseOffset >= totalCharacters)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}