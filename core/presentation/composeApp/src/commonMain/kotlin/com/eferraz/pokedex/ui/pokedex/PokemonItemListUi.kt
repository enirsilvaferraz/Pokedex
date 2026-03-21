package com.eferraz.pokedex.ui.pokedex

import androidx.compose.runtime.Immutable
import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.entity.summary.PokemonPlaceholder
import com.eferraz.pokedex.entity.summary.PokemonSummary

@Immutable
internal sealed class PokemonItemListUi {

    abstract val id: Long
    abstract val name: String
    abstract val artworkUrl: String

    data class PlaceholderRow(
        override val id: Long,
        override val name: String,
        override val artworkUrl: String,
    ) : PokemonItemListUi()

    data class SummaryRow(
        override val id: Long,
        override val name: String,
        override val artworkUrl: String,
        val type1Name: String,
        val type2Name: String?,
    ) : PokemonItemListUi()
}

internal fun BasePokemon.toPokedexListItemUi(): PokemonItemListUi = when (this) {

    is PokemonSummary -> PokemonItemListUi.SummaryRow(
        id = id,
        name = name,
        artworkUrl = artwork,
        type1Name = type1.name,
        type2Name = type2?.name,
    )

    is PokemonPlaceholder -> PokemonItemListUi.PlaceholderRow(
        id = id,
        name = name,
        artworkUrl = artwork,
    )

    else -> throw IllegalStateException("Unknown type") // todo sensivel
}
