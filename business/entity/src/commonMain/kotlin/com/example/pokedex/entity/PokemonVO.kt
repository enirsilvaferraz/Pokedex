package com.example.pokedex.entity

data class PokemonVO(
    val id: Long,
    val name: String,
    val image: String,
    val type1: TypeVO,
    val type2: TypeVO? = null
)