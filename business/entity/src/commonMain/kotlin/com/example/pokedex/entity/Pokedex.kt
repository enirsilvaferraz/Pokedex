package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    @SerialName("descriptions")
    val descriptions: List<Description>,
    @SerialName("id")
    val id: Long,
    @SerialName("is_main_series")
    val isMainSeries: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("names")
    val names: List<Name>,
    @SerialName("pokemon_entries")
    val pokemonEntries: List<PokemonEntry>,
    @SerialName("region")
    val region: Region,
    @SerialName("version_groups")
    val versionGroups: List<VersionGroup>,
)

@Serializable
data class Description(
    @SerialName("description")
    val description: String,
    @SerialName("language")
    val language: Language,
)

@Serializable
data class Language(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)

@Serializable
data class Name(
    @SerialName("language")
    val language: Language,
    @SerialName("name")
    val name: String,
)

@Serializable
data class PokemonEntry(
    @SerialName("entry_number")
    val entryNumber: Long,
    @SerialName("pokemon_species")
    val pokemonSpecies: PokemonSpecies,
)

@Serializable
data class PokemonSpecies(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)

@Serializable
data class Region(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)

@Serializable
data class VersionGroup(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)
