package com.eferraz.pokedex.entity.detail

public data class Breeding(
    val id: Long,
    val genderRatio: Float,
    val eggGroups: List<EggGroup>,
) {

    public val female: Float
        get() = genderRatio / FEMALE_RATIO

    public val male: Float
        get() = 1 - female

    private companion object {
        const val FEMALE_RATIO = 8
    }
}
