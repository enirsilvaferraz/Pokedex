package com.eferraz.pokedex.entity.summary

import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.entity.detail.Type

public data class PokemonSummary(
    override val id: Long,
    override val name: String,
    public val artwork: String = officialArtworkUrl(id),
    public val type1: Type? = null,
    public val type2: Type? = null,
) : BasePokemon {

    public fun isPlaceholder(): Boolean =
        type1 == null

    public fun types(): List<Type> =
        listOfNotNull(type1, type2)
}