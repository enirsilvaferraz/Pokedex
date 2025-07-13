package com.eferraz.pokedex.network.core

import io.ktor.client.HttpClient

internal interface ClientConfig {
    val client: HttpClient
}