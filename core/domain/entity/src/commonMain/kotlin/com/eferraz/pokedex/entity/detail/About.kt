package com.eferraz.pokedex.entity.detail

public data class About(
    val id: Long,
    val height: Float,
    val weight: Float,
    val abilities: List<Ability>,
)
