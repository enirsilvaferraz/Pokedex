package com.eferraz.pokedex.entity

public interface PokemonVO {

    public val id: Long
    public val name: String
    public val image: String
    public val type1: TypeVO
    public val type2: TypeVO?

    public fun types(): List<TypeVO> = listOfNotNull(type1, type2)
}