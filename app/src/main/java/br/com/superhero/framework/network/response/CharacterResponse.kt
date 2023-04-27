package br.com.superhero.framework.network.response

import br.com.core.domain.model.Character
import com.google.gson.annotations.SerializedName

/**
 * Created by João Bosco on 05/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun CharacterResponse.toCharacterModel(): Character {
    return Character(
        id = this.id,
        name = this.name,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}