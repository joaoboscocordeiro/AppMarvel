package br.com.testing.model

import br.com.core.domain.model.Event

/**
 * Created by João Bosco on 28/04/2023.
 */
class EventFactory {

    fun create(event: FakeEvent) = when (event) {
        FakeEvent.FEvent -> Event(
            1,
            "http://fakeeventurl.jpg"
        )
    }

    sealed class FakeEvent {
        object FEvent : FakeEvent()
    }
}