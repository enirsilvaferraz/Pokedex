package com.eferraz.pokedex.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

internal actual fun httpEngine(): HttpClientEngineFactory<*> = CIO