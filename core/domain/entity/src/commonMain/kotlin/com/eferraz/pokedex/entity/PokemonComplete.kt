package com.eferraz.pokedex.entity

public data class PokemonComplete(
    override val id: Long,
    override val name: String,
    override val image: String,
    override val type1: Type,
    override val type2: Type?,
    val about: About,
    val breeding: Breeding,
    val stats: Stats,
    val moves: List<Move>,
) : BasePokemon {

    public fun toLight(): PokemonLight = PokemonLight(id = id, name = name, image = image, type1 = type1, type2 = type2)
}