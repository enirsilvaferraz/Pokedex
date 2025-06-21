package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.Pokedex

internal interface PokedexDataSource {

    suspend fun get(): Pokedex
}

