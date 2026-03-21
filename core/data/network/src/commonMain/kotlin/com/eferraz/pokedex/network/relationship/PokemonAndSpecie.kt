package com.eferraz.pokedex.network.relationship

import com.eferraz.pokedex.entity.detail.Ability
import com.eferraz.pokedex.entity.detail.About
import com.eferraz.pokedex.entity.detail.Breeding
import com.eferraz.pokedex.entity.detail.EggGroup
import com.eferraz.pokedex.entity.detail.Move
import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.entity.detail.Stats
import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.network.responses.pokemon.PokemonResponse
import com.eferraz.pokedex.network.responses.species.PokemonSpeciesDetail

internal data class PokemonAndSpecie(
    private val pokemon: PokemonResponse,
    private val species: PokemonSpeciesDetail,
) {

    fun toModel(): PokemonDetailed = PokemonDetailed(
        id = pokemon.id,
        name = pokemon.name,
        image = pokemon.sprites.other?.officialArtwork?.frontDefault ?: pokemon.sprites.frontDefault,
        type1 = pokemon.types.first { it.slot == 1 }.let { Type(id = it.type.getId(), name = it.type.name.orEmpty()) },
        type2 = pokemon.types.firstOrNull { it.slot == 2 }?.let { Type(id = it.type.getId(), name = it.type.name.orEmpty()) },
        about = About(
            id = pokemon.id,
            description = species.flavorTextEntries.first { it.language.name == "en" }.flavorText.replace("\n", " ").replace(Regex("\\f"), " "),
            species = species.genera.first { it.language.name == "en" }.genus,
            category = species.shape?.name.orEmpty(),
            height = pokemon.height.toFloat(),
            weight = pokemon.weight.toFloat(),
            abilities = pokemon.abilities.map { Ability(id = it.ability.getId(), name = it.ability.name.orEmpty()) }
        ),
        breeding = Breeding(
            id = pokemon.id,
            genderRatio = species.genderRate.toFloat(),
            eggGroups = species.eggGroups.map { EggGroup(id = it.getId(), name = it.name.orEmpty()) },
        ),
        stats = Stats(
            id = pokemon.id,
            hp = pokemon.stats.find { it.stat.name == "hp" }?.baseStat ?: 0,
            attack = pokemon.stats.find { it.stat.name == "attack" }?.baseStat ?: 0,
            defense = pokemon.stats.find { it.stat.name == "defense" }?.baseStat ?: 0,
            specialAttack = pokemon.stats.find { it.stat.name == "special-attack" }?.baseStat ?: 0,
            specialDefense = pokemon.stats.find { it.stat.name == "special-defense" }?.baseStat ?: 0,
            speed = pokemon.stats.find { it.stat.name == "speed" }?.baseStat ?: 0
        ),
        moves = pokemon.moves.map { Move(id = it.move.getId(), name = it.move.name.orEmpty()) }
    )
}