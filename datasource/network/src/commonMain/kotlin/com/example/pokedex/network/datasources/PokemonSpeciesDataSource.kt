package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.PokemonSpeciesDetail

interface PokemonSpeciesDataSource {

    suspend fun get(id: Long): PokemonSpeciesDetail
}