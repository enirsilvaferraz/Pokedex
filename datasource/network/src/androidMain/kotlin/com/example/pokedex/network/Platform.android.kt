package com.example.pokedex.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual fun httpEngine(): HttpClientEngineFactory<*> = OkHttp