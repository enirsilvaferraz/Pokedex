package com.example.pokedex.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun platform() = "Android"
actual fun httpEngine(): HttpClientEngineFactory<*> = OkHttp