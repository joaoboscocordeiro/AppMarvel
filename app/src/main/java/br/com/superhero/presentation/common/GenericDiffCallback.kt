package br.com.superhero.presentation.common

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by João Bosco on 30/05/2023.
 */
class GenericDiffCallback<T: ListItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}