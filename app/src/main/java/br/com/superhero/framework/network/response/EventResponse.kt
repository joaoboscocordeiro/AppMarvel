package br.com.superhero.framework.network.response

import br.com.core.domain.model.Event
import com.google.gson.annotations.SerializedName

/**
 * Created by João Bosco on 26/04/2023.
 */
data class EventResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun EventResponse.toEventModel(): Event {
    return Event(
        id = this.id,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}