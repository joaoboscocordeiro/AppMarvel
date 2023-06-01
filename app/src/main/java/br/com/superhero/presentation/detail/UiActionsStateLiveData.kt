package br.com.superhero.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import br.com.core.usecase.GetCharacterCategoriesUseCase
import br.com.superhero.R
import br.com.superhero.presentation.extensions.watchStatus
import kotlin.coroutines.CoroutineContext

/**
 * Created by João Bosco on 11/05/2023.
 */
class UiActionsStateLiveData(
    private val coroutineContext: CoroutineContext,
    private val getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase
) {

    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutineContext) {
            when (it) {
                is Action.Load -> {
                    getCharacterCategoriesUseCase.invoke(
                        GetCharacterCategoriesUseCase.GetCategoriesParams(it.characterId)
                    ).watchStatus(
                        loading = {
                            emit(UiState.Loading)
                        },
                        success = { data ->
                            val detailParentList = mutableListOf<DetailParentVE>()

                            val comics = data.first
                            if (comics.isNotEmpty()) {
                                comics.map {
                                    DetailChildVE(it.id, it.imageUrl)
                                }.also {
                                    detailParentList.add(
                                        DetailParentVE(R.string.details_comics_category, it)
                                    )
                                }
                            }

                            val events = data.second
                            if (events.isNotEmpty()) {
                                events.map {
                                    DetailChildVE(it.id, it.imageUrl)
                                }.also {
                                    detailParentList.add(
                                        DetailParentVE(R.string.details_events_category, it)
                                    )
                                }
                            }

                            if (detailParentList.isNotEmpty()) {
                                emit(UiState.Success(detailParentList))
                            } else emit(UiState.Empty)
                        },
                        error = {
                            emit(UiState.Error)
                        }
                    )
                }
            }
        }
    }

    fun load(characterId: Int) {
        action.value = Action.Load(characterId)
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: List<DetailParentVE>) : UiState()
        object Empty : UiState()
        object Error : UiState()
    }

    sealed class Action {
        data class Load(val characterId: Int) : Action()
    }
}