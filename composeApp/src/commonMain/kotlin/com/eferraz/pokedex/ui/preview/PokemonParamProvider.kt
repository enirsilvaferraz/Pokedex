package com.eferraz.pokedex.ui.preview

import androidx.compose.ui.graphics.Color
import com.eferraz.pokedex.ui.detail.PokemonView
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class PokemonParamProvider() : PreviewParameterProvider<PokemonView> {
    override val values: Sequence<PokemonView> = sequenceOf(pokemon)
}

internal val pokemon = PokemonView(
    id = "#001",
    name = "Bulbasaur",
    types = listOf("Grass", "Poison"),
    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
    description = "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
    color = Color(0xFF7AC74C),
    about = PokemonView.About(
        species = "Seed Pokemon",
        category = "Quadruped",
        height = "0.70 cm",
        weight = "6.9 kg",
        abilities = "Chlorophyll, Overgrow"
    ),
    breeding = PokemonView.Breeding(
        gender = "87,5% Male, 12,5% Female",
        eggGroups = "Monster",
        eggCycle = "Grass"
    ),
    stats = PokemonView.Stats(
        hp = PokemonView.Stats.Item(45),
        attack = PokemonView.Stats.Item(49),
        defense = PokemonView.Stats.Item(4),
        spAtk = PokemonView.Stats.Item(65),
        spDef = PokemonView.Stats.Item(65),
        speed = PokemonView.Stats.Item(45)
    ),
    abilities = listOf(
        PokemonView.Ability(14L, "Swords-Dance"),
        PokemonView.Ability(15L, "Cut"),
        PokemonView.Ability(20L, "Vine-Whip"),
        PokemonView.Ability(21L, "Fly"),
        PokemonView.Ability(22L, "Tackle"),
        PokemonView.Ability(25L, "Body-Slam")
    )
)
