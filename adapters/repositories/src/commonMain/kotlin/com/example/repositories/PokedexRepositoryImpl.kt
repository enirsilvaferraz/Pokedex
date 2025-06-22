package com.example.repositories

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.network.adapters.PokedexAdapter
import kotlinx.coroutines.flow.Flow

internal class PokedexRepositoryImpl(
    private val adapter: PokedexAdapter
) : PokedexRepository {

    override fun get(): Flow<PokemonVO> = adapter.get()
}