package com.example.repositories.datasources

import com.example.pokedex.entity.PokedexVO
import kotlinx.coroutines.flow.Flow

interface PokedexDataSource {

    interface Network {
        suspend fun get(id: Int): Flow<PokedexVO>
    }
}