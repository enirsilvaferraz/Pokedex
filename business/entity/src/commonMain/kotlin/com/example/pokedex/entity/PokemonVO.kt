package com.example.pokedex.entity

data class PokemonVO(
    val id: Long,
    val name: String,
    val image: String,
    val types: List<TypeVO>,
)

data class TypeVO(
    val id: Long,
    val name: String,
)