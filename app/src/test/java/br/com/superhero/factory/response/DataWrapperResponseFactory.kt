package br.com.superhero.factory.response

import br.com.superhero.framework.network.response.CharacterResponse
import br.com.superhero.framework.network.response.DataContainerResponse
import br.com.superhero.framework.network.response.DataWrapperResponse
import br.com.superhero.framework.network.response.ThumbnailResponse

/**
 * Created by João Bosco on 05/04/2023.
 */
class DataWrapperResponseFactory {

    fun create() = DataWrapperResponse(
        copyright = "",
        data = DataContainerResponse(
            offset = 0,
            total = 2,
            results = listOf(
                CharacterResponse(
                    id = "1011334",
                    name = "3-D Man",
                    thumbnail = ThumbnailResponse(
                        path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                        extension = "jpg"
                    )
                ),
                CharacterResponse(
                    id = "1017100",
                    name = "A-Bomb (HAS)",
                    thumbnail = ThumbnailResponse(
                        path = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
                        extension = "jpg"
                    )
                )
            )
        )
    )
}