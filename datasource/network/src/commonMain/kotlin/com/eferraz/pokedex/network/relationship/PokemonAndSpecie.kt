package com.eferraz.pokedex.network.relationship

import com.eferraz.pokedex.entity.AbilityVO
import com.eferraz.pokedex.entity.AboutVO
import com.eferraz.pokedex.entity.BreedingVO
import com.eferraz.pokedex.entity.EggGroupVO
import com.eferraz.pokedex.entity.MoveVO
import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.StatsVO
import com.eferraz.pokedex.entity.TypeVO
import com.eferraz.pokedex.network.responses.pokemon.Pokemon
import com.eferraz.pokedex.network.responses.species.PokemonSpeciesDetail

internal data class PokemonAndSpecie(
    private val pokemon: Pokemon,
    private val species: PokemonSpeciesDetail,
) {

    fun toModel(): PokemonCompleteVO = PokemonCompleteVO(
        id = pokemon.id,
        name = pokemon.name,
        image = pokemon.sprites.other?.officialArtwork?.frontDefault ?: pokemon.sprites.frontDefault,
        type1 = pokemon.types.first { it.slot == 1 }.let { TypeVO(id = it.type.getId(), name = it.type.name.orEmpty()) },
        type2 = pokemon.types.firstOrNull { it.slot == 2 }?.let { TypeVO(id = it.type.getId(), name = it.type.name.orEmpty()) },
        about = AboutVO(
            id = pokemon.id,
            description = species.flavorTextEntries.first { it.language.name == "en" }.flavorText.replace("\n", " ").replace(Regex("\\f"), " "),
            species = species.genera.first { it.language.name == "en" }.genus,
            category = species.shape?.name.orEmpty(),
            height = pokemon.height.toFloat() / 10,
            weight = pokemon.weight.toFloat() / 10,
            abilities = pokemon.abilities.map { AbilityVO(id = it.ability.getId(), name = it.ability.name.orEmpty()) }
        ),
        breeding = BreedingVO(
            id = pokemon.id,
            male = species.genderRate.toFloat(),
            eggGroups = species.eggGroups.map { EggGroupVO(id = it.getId(), name = it.name.orEmpty()) },
        ),
        stats = StatsVO(
            id = pokemon.id,
            hp = pokemon.stats.find { it.stat.name == "hp" }?.baseStat ?: 0,
            attack = pokemon.stats.find { it.stat.name == "attack" }?.baseStat ?: 0,
            defense = pokemon.stats.find { it.stat.name == "defense" }?.baseStat ?: 0,
            specialAttack = pokemon.stats.find { it.stat.name == "special-attack" }?.baseStat ?: 0,
            specialDefense = pokemon.stats.find { it.stat.name == "special-defense" }?.baseStat ?: 0,
            speed = pokemon.stats.find { it.stat.name == "speed" }?.baseStat ?: 0,
            total = pokemon.stats.sumOf { it.baseStat }
        ),
        moves = pokemon.moves.map { MoveVO(id = it.move.getId(), name = it.move.name.orEmpty()) }
    )
}