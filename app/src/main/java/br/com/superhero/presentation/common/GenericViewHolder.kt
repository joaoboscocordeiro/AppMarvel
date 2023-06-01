package br.com.superhero.presentation.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by João Bosco on 30/05/2023.
 */
abstract class GenericViewHolder<T>(
    itemBinding: ViewBinding
) : RecyclerView.ViewHolder(itemBinding.root) {
    abstract fun bind(data: T)
}