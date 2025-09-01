package com.eferraz.pokedex.entity

public data class BreedingVO(
    val id: Long,
    val genderRatio: Float,
    val eggGroups: List<EggGroupVO>,
) {
    public val female: Float get() = genderRatio / 8
    public val male: Float get() = 1 - female
}