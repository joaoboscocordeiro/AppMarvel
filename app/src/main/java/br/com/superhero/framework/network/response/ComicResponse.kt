package br.com.superhero.framework.network.response

import br.com.core.domain.model.Comic
import com.google.gson.annotations.SerializedName

/**
 * Created by João Bosco on 24/04/2023.
 */
data class ComicResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun ComicResponse.toComicModel(): Comic {
    return Comic(
        id = this.id,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}
