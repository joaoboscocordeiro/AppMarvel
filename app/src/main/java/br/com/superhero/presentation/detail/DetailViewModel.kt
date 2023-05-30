package br.com.superhero.presentation.detail

import androidx.lifecycle.ViewModel
import br.com.core.usecase.AddFavoriteUseCase
import br.com.core.usecase.GetCharacterCategoriesUseCase
import br.com.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val categories = UiActionsStateLiveData(
        coroutinesDispatchers.main(),
        getCharacterCategoriesUseCase
    )

    val favorite = FavoriteUiActionStateLiveData(
        coroutinesDispatchers.main(),
        addFavoriteUseCase
    )

    init {
        favorite.setDefault()
    }
}