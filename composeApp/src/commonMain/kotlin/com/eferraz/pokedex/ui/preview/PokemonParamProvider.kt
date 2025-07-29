package com.eferraz.pokedex.ui.preview

import androidx.compose.ui.graphics.Color
import com.eferraz.pokedex.ui.detail.vos.FieldValueVo
import com.eferraz.pokedex.ui.detail.vos.PokemonDetailVo
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokeball

internal class PokemonParamProvider() : PreviewParameterProvider<PokemonDetailVo> {
    override val values: Sequence<PokemonDetailVo> = sequenceOf(pokemon)
}

internal val pokemon = PokemonDetailVo(
    background = PokemonDetailVo.Background(Color(0xFF7AC74C)),
    header = PokemonDetailVo.Header(id = "#001", name = "Bulbasaur"),
    types = PokemonDetailVo.Types(types = listOf("Grass", "Poison")),
    image = PokemonDetailVo.ImageVo.Local(Res.drawable.pokeball, "pokeball"),
    description = "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
    about = PokemonDetailVo.About(
        listOf(
            FieldValueVo("Species", "Seed Pokemon"),
            FieldValueVo("Category", "Quadruped"),
            FieldValueVo("Height", "0.70 cm"),
            FieldValueVo("Weight", "6.9 kg"),
            FieldValueVo("Abilities", "Chlorophyll")
        )
    ),
    breeding = PokemonDetailVo.Breeding(
        listOf(
            FieldValueVo("Gender", "87,5% Male, 12,5% Female"),
            FieldValueVo("Egg Groups", "Monster"),
            FieldValueVo("Egg Cycle", "Grass")
        )
    ),
    stats = PokemonDetailVo.Stats(
        listOf(
            PokemonDetailVo.Stats.ChartItem("HP", 45),
            PokemonDetailVo.Stats.ChartItem("Attack", 49),
            PokemonDetailVo.Stats.ChartItem("Defense", 49),
            PokemonDetailVo.Stats.ChartItem("Sp. Atk", 65),
            PokemonDetailVo.Stats.ChartItem("Sp. Def", 65),
            PokemonDetailVo.Stats.ChartItem("Speed", 45),
            PokemonDetailVo.Stats.ChartItem("Total", 318, 600)
        )
    ),
    moves = PokemonDetailVo.Moves(
        listOf(
            FieldValueVo(1L.toString(), "Chlorophyll"),
            FieldValueVo(2L.toString(), "Overgrow"),
            FieldValueVo(3L.toString(), "Blaze"),
            FieldValueVo(4L.toString(), "Torrent"),
            FieldValueVo(5L.toString(), "Solar Power"),
            FieldValueVo(6L.toString(), "Drizzle"),
            FieldValueVo(7L.toString(), "Earth Power"),
            FieldValueVo(8L.toString(), "Lightning Rod")
        )
    )
)
