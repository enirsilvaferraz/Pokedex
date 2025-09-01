package com.eferraz.pokedex.entity

public data class PokemonLightVO(
    override val id: Long,
    override val name: String,
    override val image: String,
    override val type1: TypeVO,
    override val type2: TypeVO?,
) : PokemonVO