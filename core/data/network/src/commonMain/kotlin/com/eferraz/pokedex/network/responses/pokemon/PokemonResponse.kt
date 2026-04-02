package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.entity.detail.Ability
import com.eferraz.pokedex.entity.detail.About
import com.eferraz.pokedex.entity.detail.Move
import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.entity.detail.Stats
import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonResponse(
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
    val weight: Int,
) {

    fun toModel(): PokemonDetailed =
        PokemonDetailed(
            id = id,
            name = name,
            image = sprites.other?.officialArtwork?.frontDefault ?: sprites.frontDefault,
            type1 = types.first { it.slot == 1 }.let { Type(id = it.type.getId(), name = it.type.name.orEmpty()) },
            type2 = types.firstOrNull { it.slot == 2 }?.let {
                Type(
                    id = it.type.getId(),
                    name = it.type.name.orEmpty()
                )
            },
            species = null,
            about = About(
                id = id,
                height = height.toFloat(),
                weight = weight.toFloat(),
                abilities = abilities.map { Ability(id = it.ability.getId(), name = it.ability.name.orEmpty()) }
            ),
            stats = Stats(
                id = id,
                hp = stats.find { it.stat.name == "hp" }?.baseStat ?: 0,
                attack = stats.find { it.stat.name == "attack" }?.baseStat ?: 0,
                defense = stats.find { it.stat.name == "defense" }?.baseStat ?: 0,
                specialAttack = stats.find { it.stat.name == "special-attack" }?.baseStat ?: 0,
                specialDefense = stats.find { it.stat.name == "special-defense" }?.baseStat ?: 0,
                speed = stats.find { it.stat.name == "speed" }?.baseStat ?: 0
            ),
            moves = moves.map { Move(id = it.move.getId(), name = it.move.name.orEmpty()) }
        )
}
