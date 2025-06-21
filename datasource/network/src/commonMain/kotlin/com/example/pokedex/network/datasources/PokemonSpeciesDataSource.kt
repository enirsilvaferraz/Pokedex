package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.PokemonSpeciesDetail

internal interface PokemonSpeciesDataSource {

    suspend fun get(id: Long): PokemonSpeciesDetail
}