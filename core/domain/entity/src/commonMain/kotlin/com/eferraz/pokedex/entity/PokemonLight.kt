package com.eferraz.pokedex.entity

public data class PokemonLight(
    override val id: Long,
    override val name: String,
    override val image: String,
    override val type1: Type,
    override val type2: Type?,
) : BasePokemon