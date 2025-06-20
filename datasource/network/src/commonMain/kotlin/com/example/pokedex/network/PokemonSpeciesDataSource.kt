package com.example.pokedex.network

import com.example.pokedex.entity.PokemonSpeciesDetail

interface PokemonSpeciesDataSource {

    suspend fun get(id: Long): PokemonSpeciesDetail
}