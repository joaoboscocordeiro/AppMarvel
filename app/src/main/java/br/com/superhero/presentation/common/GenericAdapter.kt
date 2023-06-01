package br.com.superhero.presentation.common

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

/**
 * Created by João Bosco on 30/05/2023.
 */

inline fun <T : ListItem, VH : GenericViewHolder<T>> getGenericAdapterOf(
    crossinline createViewHolder: (ViewGroup) -> VH
): ListAdapter<T, VH> {

    val diff = GenericDiffCallback<T>()

    return object : ListAdapter<T, VH>(diff) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return createViewHolder(parent)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(getItem(position))
        }
    }
}