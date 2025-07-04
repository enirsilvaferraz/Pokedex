package com.example.pokedex.entity

 data class PokedexVO(
    val id: Long,
    val name: String,
    val pokemon: List<PokemonVO>,
)