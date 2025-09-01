package com.eferraz.pokedex.network

import io.ktor.client.engine.HttpClientEngineFactory

internal expect fun httpEngine(): HttpClientEngineFactory<*>