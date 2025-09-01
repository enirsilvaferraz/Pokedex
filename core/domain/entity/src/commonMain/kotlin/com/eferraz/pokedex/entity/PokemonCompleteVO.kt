package com.eferraz.pokedex.entity

public data class PokemonCompleteVO(
    override val id: Long,
    override val name: String,
    override val image: String,
    override val type1: TypeVO,
    override val type2: TypeVO?,
    val about: AboutVO,
    val breeding: BreedingVO,
    val stats: StatsVO,
    val moves: List<MoveVO>,
) : PokemonVO {

    public fun toLight(): PokemonLightVO = PokemonLightVO(id = id, name = name, image = image, type1 = type1, type2 = type2)
}