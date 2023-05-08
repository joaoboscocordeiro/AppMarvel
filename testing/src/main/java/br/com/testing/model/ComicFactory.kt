package br.com.testing.model

import br.com.core.domain.model.Comic

/**
 * Created by João Bosco on 28/04/2023.
 */
class ComicFactory {

    fun create(comic: FakeComic) = when (comic) {
        FakeComic.FComic -> Comic(
            2211506,
            "http://fakecomicurl.jpg"
        )
    }

    sealed class FakeComic {
        object FComic : FakeComic()
    }
}