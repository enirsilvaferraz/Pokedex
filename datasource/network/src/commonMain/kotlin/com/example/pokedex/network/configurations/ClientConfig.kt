package com.example.pokedex.network.configurations

import io.ktor.client.HttpClient

 interface ClientConfig {
    val client: HttpClient
}