package com.example.pokedex.network

import com.example.pokedex.entity.Pokedex

interface PokedexDataSource {

    suspend fun get(): Pokedex
}

