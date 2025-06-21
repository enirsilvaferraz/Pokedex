package com.example.pokedex.network.configurations

import io.ktor.client.HttpClient

 internal interface ClientConfig {
    val client: HttpClient
}