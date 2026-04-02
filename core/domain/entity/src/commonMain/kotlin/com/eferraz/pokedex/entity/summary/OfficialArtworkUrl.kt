package com.eferraz.pokedex.entity.summary

internal const val OFFICIAL_ARTWORK_BASE_URL: String =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

public fun officialArtworkUrl(id: Long): String =
    "$OFFICIAL_ARTWORK_BASE_URL$id.png"
