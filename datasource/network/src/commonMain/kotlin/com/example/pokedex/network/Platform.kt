package com.example.pokedex.network

import io.ktor.client.engine.HttpClientEngineFactory

internal expect fun httpEngine(): HttpClientEngineFactory<*>