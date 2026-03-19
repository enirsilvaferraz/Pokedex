package com.eferraz.pokedex.entity

public interface BasePokemon {

    public val id: Long
    public val name: String
    public val image: String
    public val type1: Type
    public val type2: Type?

    public fun types(): List<Type> = listOfNotNull(type1, type2)
}