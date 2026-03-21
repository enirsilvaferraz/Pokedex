package com.eferraz.pokedex.entity.detail

public data class About(
    val id: Long,
    val description: String,
    val species: String,
    val category: String,
    val height: Float,
    val weight: Float,
    val abilities: List<Ability>,
)