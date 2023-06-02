package br.com.superhero.presentation.characters.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * Created by João Bosco on 14/09/2022.
 */
class CharactersRefreshStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersRefreshStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = CharactersRefreshStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: CharactersRefreshStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}