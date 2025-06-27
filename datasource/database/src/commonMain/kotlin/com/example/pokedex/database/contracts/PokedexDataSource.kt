package com.example.pokedex.database.contracts

import com.example.pokedex.entity.PokedexVO

interface PokedexDataSource {

    fun insert(pokedex: PokedexVO)

    fun getAll(): List<PokedexVO>
}