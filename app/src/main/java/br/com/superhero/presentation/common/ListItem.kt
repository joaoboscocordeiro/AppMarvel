package br.com.superhero.presentation.common

/**
 * Created by João Bosco on 30/05/2023.
 */
interface ListItem {
    val key: Long
    fun areItemsTheSame(other: ListItem) = this.key == other.key
    fun areContentsTheSame(other: ListItem) = this == other
}