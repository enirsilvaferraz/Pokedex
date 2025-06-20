package com.example.pokedex.network

import com.example.pokedex.entity.Pokemon

interface PokemonDataSource {
    suspend fun get(id: Long): Pokemon
}