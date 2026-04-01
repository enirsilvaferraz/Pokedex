package com.eferraz.pokedex.ui.detail.data

import androidx.compose.ui.graphics.Color
import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.helpers.capitalizeName
import com.eferraz.pokedex.helpers.formatHeight
import com.eferraz.pokedex.helpers.formatID
import com.eferraz.pokedex.helpers.formatList
import com.eferraz.pokedex.helpers.formatPercent
import com.eferraz.pokedex.helpers.formatWeight
import org.jetbrains.compose.resources.StringResource
import pokedex.features.composeapp.generated.resources.Res
import pokedex.features.composeapp.generated.resources.detail_about_abilities
import pokedex.features.composeapp.generated.resources.detail_about_category
import pokedex.features.composeapp.generated.resources.detail_about_height
import pokedex.features.composeapp.generated.resources.detail_about_species
import pokedex.features.composeapp.generated.resources.detail_about_weight
import pokedex.features.composeapp.generated.resources.detail_breeding_egg_groups
import pokedex.features.composeapp.generated.resources.detail_breeding_female
import pokedex.features.composeapp.generated.resources.detail_breeding_male
import pokedex.features.composeapp.generated.resources.detail_stats_atk
import pokedex.features.composeapp.generated.resources.detail_stats_def
import pokedex.features.composeapp.generated.resources.detail_stats_hp
import pokedex.features.composeapp.generated.resources.detail_stats_sp_atk
import pokedex.features.composeapp.generated.resources.detail_stats_sp_def
import pokedex.features.composeapp.generated.resources.detail_stats_speed
import pokedex.features.composeapp.generated.resources.detail_stats_total
import kotlin.jvm.JvmInline

internal data class PokemonDetailDataView(
    val types: List<Type>,
    val image: ArtWork,
    val description: String,
    val about: List<FieldValue>,
    val breeding: List<FieldValue>,
    val stats: List<Stat>,
    val moves: List<Move>,
) {

    constructor(model: PokemonDetailed) : this(
        types = model.types().map { Type(it) },
        image = ArtWork(model),
        description = model.species?.description.orEmpty(),
        about = listOf(
            FieldValue(Res.string.detail_about_species, model.species?.species.orEmpty().capitalizeName()),
            FieldValue(Res.string.detail_about_category, model.species?.category.orEmpty().capitalizeName()),
            FieldValue(Res.string.detail_about_height, model.about.height.formatHeight()),
            FieldValue(Res.string.detail_about_weight, model.about.weight.formatWeight()),
            FieldValue(Res.string.detail_about_abilities, model.about.abilities.map { it.name }.formatList())
        ),
        breeding = model.species?.breeding?.let { b ->
            listOf(
                FieldValue(Res.string.detail_breeding_male, b.male.formatPercent()),
                FieldValue(Res.string.detail_breeding_female, b.female.formatPercent()),
                FieldValue(Res.string.detail_breeding_egg_groups, b.eggGroups.map { it.name }.formatList())
            )
        }.orEmpty(),
        stats = listOf(
            Stat(Res.string.detail_stats_hp, model.stats.hp),
            Stat(Res.string.detail_stats_atk, model.stats.attack),
            Stat(Res.string.detail_stats_def, model.stats.defense),
            Stat(Res.string.detail_stats_sp_atk, model.stats.specialAttack),
            Stat(Res.string.detail_stats_sp_def, model.stats.specialDefense),
            Stat(Res.string.detail_stats_speed, model.stats.speed),
            Stat(Res.string.detail_stats_total, model.stats.total, 600)
        ),
        moves = model.moves.map { Move(it) }
    )

    @JvmInline
    internal value class Type(
        val name: String,
    ) {
        constructor(model: com.eferraz.pokedex.entity.detail.Type) : this(
            name = model.name.capitalizeName()
        )
    }

    internal data class ArtWork(
        val url: String,
        val contentDescription: String,
    ) {
        constructor(model: PokemonDetailed) : this(
            url = model.artwork,
            contentDescription = model.name
        )
    }

    internal class FieldValue(
        val field: StringResource,
        val value: String,
    )

    internal data class Stat(
        val field: StringResource,
        val value: String,
        val progress: Float,
        val color: Color,
    ) {
        constructor(field: StringResource, value: Int, max: Int = 100) : this(
            field = field,
            value = value.toString(),
            progress = value.toFloat() / max.toFloat(),
            color = if (value > max / 2) Color.Green else Color.Red
        )
    }

    internal data class Move(
        val id: String,
        val name: String,
    ) {
        constructor(model: com.eferraz.pokedex.entity.detail.Move) : this(
            id = model.id.formatID(),
            name = model.name.capitalizeName()
        )
    }
}
