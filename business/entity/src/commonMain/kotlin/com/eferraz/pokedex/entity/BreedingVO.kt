package com.eferraz.pokedex.entity

public data class BreedingVO(
    val id: Long,
    val male: Float,
    val female: Float = 1 - male,
    val eggGroups: List<EggGroupVO>,
)