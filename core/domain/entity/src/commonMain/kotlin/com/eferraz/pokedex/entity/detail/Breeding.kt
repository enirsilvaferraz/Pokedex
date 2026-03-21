package com.eferraz.pokedex.entity.detail

public data class Breeding(
    val id: Long,
    val genderRatio: Float,
    val eggGroups: List<EggGroup>,
) {
    public val female: Float get() = genderRatio / 8
    public val male: Float get() = 1 - female
}