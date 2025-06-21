package com.example.pokedex.entity

data class PokemonVO(
    val id: Int,
    val name: String,
    val types: List<String>,
    val url: String// = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
)