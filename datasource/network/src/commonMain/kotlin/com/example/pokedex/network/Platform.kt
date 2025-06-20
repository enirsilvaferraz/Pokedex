package com.example.pokedex.network

import io.ktor.client.engine.HttpClientEngineFactory

expect fun platform(): String

expect fun httpEngine(): HttpClientEngineFactory<*>