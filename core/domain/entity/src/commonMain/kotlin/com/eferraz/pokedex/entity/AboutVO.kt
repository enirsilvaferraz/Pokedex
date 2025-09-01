package com.eferraz.pokedex.entity

public data class AboutVO(
    val id: Long,
    val description: String,
    val species: String,
    val category: String,
    val height: Float,
    val weight: Float,
    val abilities: List<AbilityVO>,
)