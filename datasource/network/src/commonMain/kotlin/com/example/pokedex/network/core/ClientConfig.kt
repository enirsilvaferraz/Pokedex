package com.example.pokedex.network.core

import io.ktor.client.HttpClient

internal interface ClientConfig {
    val client: HttpClient
}