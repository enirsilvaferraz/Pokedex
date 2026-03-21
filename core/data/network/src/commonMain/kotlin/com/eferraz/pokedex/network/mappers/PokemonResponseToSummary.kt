package com.eferraz.pokedex.network.mappers

import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.entity.summary.PokemonSummary
import com.eferraz.pokedex.network.responses.NamedApiResource
import com.eferraz.pokedex.network.responses.pokemon.PokemonResponse

internal fun toModel(response: PokemonResponse): PokemonSummary {

    val sorted = response.types.sortedBy { it.slot }

    val primary = sorted.first()
    val secondary = sorted.getOrNull(1)

    return PokemonSummary(
        id = response.id,
        name = response.name,
        type1 = Type(id = primary.type.getId(), name = primary.type.name.orEmpty()),
        type2 = secondary?.let { Type(id = it.type.getId(), name = it.type.name.orEmpty()) },
    )
}

internal fun toModel(resource: NamedApiResource) =
    PokemonSummary(
        id = resource.getId(),
        name = resource.name.orEmpty(),
    )

