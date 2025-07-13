package com.eferraz.pokedex.network.helpers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal fun <T : Any> emitFlow(body: suspend () -> T) = flow {
    emit(body())
}.flowOn(Dispatchers.IO)
