package com.example.pokedex.entity

data class PokedexVO(
    val id: Int,
    val name: String,
    val pokemon: List<PokemonVO>
)