package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.Pokedex

internal interface PokedexApi {

    suspend fun get(): Pokedex
}

