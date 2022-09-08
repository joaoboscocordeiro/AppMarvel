package br.com.superhero.framework.network.response

/**
 * Created by João Bosco on 05/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
data class CharacterResponse(
    val id: String,
    val name: String,
    val thumbnail: ThumbnailResponse
)