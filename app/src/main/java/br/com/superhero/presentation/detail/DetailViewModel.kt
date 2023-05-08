package br.com.superhero.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.core.domain.model.Comic
import br.com.core.domain.model.Event
import br.com.core.usecase.GetCharacterCategoriesUseCase
import br.com.core.usecase.base.ResultStatus
import br.com.superhero.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getCharacterCategories(characterId: Int) = viewModelScope.launch {
        getCharacterCategoriesUseCase(GetCharacterCategoriesUseCase.GetCategoriesParams(characterId))
            .watchStatus()
    }

    private fun Flow<ResultStatus<Pair<List<Comic>, List<Event>>>>.watchStatus() =
        viewModelScope.launch {
            collect { status ->
                _uiState.value = when (status) {
                    ResultStatus.Loanding -> UiState.Loading
                    is ResultStatus.Success -> {
                        val detailParentList = mutableListOf<DetailParentVE>()

                        val comics = status.data.first
                        if (comics.isNotEmpty()) {
                            comics.map {
                                DetailChildVE(it.id, it.imageUrl)
                            }.also {
                                detailParentList.add(
                                    DetailParentVE(R.string.details_comics_category, it)
                                )
                            }
                        }

                        val events = status.data.second
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
                            UiState.Success(detailParentList)
                        } else UiState.Empty
                    }
                    is ResultStatus.Error -> UiState.Error
                }
            }
        }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: List<DetailParentVE>) : UiState()
        object Empty : UiState()
        object Error : UiState()
    }
}