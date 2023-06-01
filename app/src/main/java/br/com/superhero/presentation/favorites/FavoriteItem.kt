package br.com.superhero.presentation.favorites

import br.com.superhero.presentation.common.ListItem

/**
 * Created by João Bosco on 01/06/2023.
 */
data class FavoriteItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    override val key: Long = id.toLong()
) : ListItem
