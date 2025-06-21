package com.example.pokedex.list

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.network.adapters.PokedexAdapter
import kotlinx.coroutines.flow.Flow

internal interface PokedexRepository {
    fun get(): Flow<PokemonVO>
}

internal class PokedexRepositoryImpl(
    private val adapter: PokedexAdapter
) : PokedexRepository {

    override fun get(): Flow<PokemonVO> = adapter.get()
}