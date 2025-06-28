package com.example.pokedex.entity

data class PokemonVO(
    val id: Long,
    val name: String,
    val image: String,
    val types: List<String>
) {

    fun imageUrl() =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}