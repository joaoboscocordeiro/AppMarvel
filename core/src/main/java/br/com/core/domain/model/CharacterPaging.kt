package br.com.core.domain.model

/**
 * Created by João Bosco on 20/04/2023.
 */
data class CharacterPaging(
    val offset: Int,
    val total: Int,
    val characters: List<Character>
)
