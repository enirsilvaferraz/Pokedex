package com.eferraz.pokedex.network.mappers

import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.entity.summary.PokemonSummary
import com.eferraz.pokedex.network.responses.NamedApiResource
import com.eferraz.pokedex.network.responses.pokemon.PokemonResponse

internal fun PokemonResponse.toPokemonSummary(): PokemonSummary {
    val sorted = types.sortedBy { it.slot }
    val primary = sorted.firstOrNull() ?: error("Pokémon $id sem tipo na resposta da API")
    val secondary = sorted.getOrNull(1)
    return PokemonSummary(
        id = id,
        name = name,
        type1 = Type(id = primary.type.getId(), name = primary.type.name.orEmpty()),
        artwork = sprites.other?.officialArtwork?.frontDefault ?: sprites.frontDefault,
        type2 = secondary?.let { Type(id = it.type.getId(), name = it.type.name.orEmpty()) },
    )
}

internal fun toPokemonSummaryFromNamedResource(resource: NamedApiResource): PokemonSummary =
    PokemonSummary(
        id = resource.getId(),
        name = resource.name.orEmpty(),
    )

