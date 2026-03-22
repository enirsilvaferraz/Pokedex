package com.eferraz.pokedex.entity.detail

public data class Species(
    val id: Long,
    val description: String,
    val species: String,
    val category: String,
    val breeding: Breeding,
)
