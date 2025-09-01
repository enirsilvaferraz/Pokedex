package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.pokemon.AbilityEntry
import com.eferraz.pokedex.network.responses.pokemon.Cries
import com.eferraz.pokedex.network.responses.pokemon.GameIndex
import com.eferraz.pokedex.network.responses.pokemon.HeldItem
import com.eferraz.pokedex.network.responses.pokemon.MoveEntry
import com.eferraz.pokedex.network.responses.NamedApiResource
import com.eferraz.pokedex.network.responses.pokemon.PastAbilityEntry
import com.eferraz.pokedex.network.responses.pokemon.PastTypeEntry
import com.eferraz.pokedex.network.responses.pokemon.Sprites
import com.eferraz.pokedex.network.responses.pokemon.StatEntry
import com.eferraz.pokedex.network.responses.pokemon.TypeEntry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Pokemon(
    @SerialName("abilities")
    val abilities: List<AbilityEntry>,
    @SerialName("base_experience")
    val baseExperience: Int?,
    @SerialName("cries")
    val cries: Cries?,
    @SerialName("forms")
    val forms: List<NamedApiResource>,
    @SerialName("game_indices")
    val gameIndices: List<GameIndex>,
    @SerialName("height")
    val height: Int,
    @SerialName("held_items")
    val heldItems: List<HeldItem>,
    @SerialName("id")
    val id: Long,
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerialName("moves")
    val moves: List<MoveEntry>,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("past_abilities")
    val pastAbilities: List<PastAbilityEntry>,
    @SerialName("past_types")
    val pastTypes: List<PastTypeEntry>,
    @SerialName("species")
    val species: NamedApiResource,
    @SerialName("sprites")
    val sprites: Sprites,
    @SerialName("stats")
    val stats: List<StatEntry>,
    @SerialName("types")
    val types: List<TypeEntry>,
    @SerialName("weight")
    val weight: Int
)