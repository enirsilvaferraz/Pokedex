package com.eferraz.pokedex.core.params

import com.eferraz.pokedex.entity.summary.PokemonSummary
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonSummaryParam(
    val id: Long,
    val name: String,
    val artwork: String,
    val type1: String?,
) {

    companion object {

        fun PokemonSummary.toParam() = PokemonSummaryParam(
            id = id,
            name = name,
            artwork = artwork,
            type1 = type1?.name
        )
    }
}
