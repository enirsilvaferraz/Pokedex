package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.Pokedex

interface PokedexDataSource {

    suspend fun get(): Pokedex
}

