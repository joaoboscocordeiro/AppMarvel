package br.com.superhero.presentation.characters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * Created by João Bosco on 14/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
class CharactersLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersLoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = CharactersLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: CharactersLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}