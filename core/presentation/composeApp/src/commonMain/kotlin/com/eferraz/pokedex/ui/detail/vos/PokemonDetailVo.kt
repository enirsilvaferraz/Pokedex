package com.eferraz.pokedex.ui.detail.vos

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.eferraz.pokedex.entity.MoveVO
import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.entity.TypeVO
import com.eferraz.pokedex.helpers.formatDecimal
import org.jetbrains.compose.resources.StringResource
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.detail_about_abilities
import pokedex.composeapp.generated.resources.detail_about_category
import pokedex.composeapp.generated.resources.detail_about_height
import pokedex.composeapp.generated.resources.detail_about_species
import pokedex.composeapp.generated.resources.detail_about_weight
import pokedex.composeapp.generated.resources.detail_breeding_egg_groups
import pokedex.composeapp.generated.resources.detail_breeding_female
import pokedex.composeapp.generated.resources.detail_breeding_male
import pokedex.composeapp.generated.resources.detail_stats_atk
import pokedex.composeapp.generated.resources.detail_stats_def
import pokedex.composeapp.generated.resources.detail_stats_hp
import pokedex.composeapp.generated.resources.detail_stats_sp_atk
import pokedex.composeapp.generated.resources.detail_stats_sp_def
import pokedex.composeapp.generated.resources.detail_stats_speed
import pokedex.composeapp.generated.resources.detail_stats_total
import kotlin.jvm.JvmInline

internal typealias FieldValueVo = Pair<String, String>

internal data class PokemonDetailVo(
    val background: Background,
    val header: Header,
    val types: List<Type>,
    val image: ArtWork,
    val description: String,
    val about: List<FieldValue>,
    val breeding: List<FieldValue>,
    val stats: List<Stat>,
    val moves: List<Move>,
) {

    constructor(model: PokemonCompleteVO) : this(
        background = Background(model),
        header = Header(model),
        types = model.types().map { Type(it) },
        image = ArtWork(model),
        description = model.about.description,
        about = listOf(
            FieldValue(Res.string.detail_about_species, model.about.species.capitalize()),
            FieldValue(Res.string.detail_about_category, model.about.category.capitalize()),
            FieldValue(Res.string.detail_about_height, model.about.height.formatHeight()),
            FieldValue(Res.string.detail_about_weight, model.about.weight.formatWeight()),
            FieldValue(Res.string.detail_about_abilities, model.about.abilities.map { it.name }.formatList())
        ),
        breeding = listOf(
            FieldValue(Res.string.detail_breeding_male, model.breeding.male.formatPercent()),
            FieldValue(Res.string.detail_breeding_female, model.breeding.female.formatPercent()),
            FieldValue(Res.string.detail_breeding_egg_groups, model.breeding.eggGroups.map { it.name }.formatList())
        ),
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

    /**
     * Background area
     */
    @JvmInline
    value class Background(
        val color: Color,
    ) {
        constructor(model: PokemonVO) : this(
            color = when (model.type1.name.lowercase()) {
                "normal" -> Color(0xFFA8A77A)    // Normal - Grayish Beige
                "fire" -> Color(0xFFEE8130)      // Fire - Orange-Red
                "water" -> Color(0xFF6390F0)     // Water - Blue
                "electric" -> Color(0xFFF7D02C)  // Electric - Yellow
                "grass" -> Color(0xFF7AC74C)     // Grass - Green
                "ice" -> Color(0xFF96D9D6)       // Ice - Light Cyan
                "fighting" -> Color(0xFFC22E28)  // Fighting - Dark Red
                "poison" -> Color(0xFFA33EA1)    // Poison - Purple
                "ground" -> Color(0xFFE2BF65)    // Ground - Light Brown
                "flying" -> Color(0xFFA98FF3)    // Flying - Lavender
                "psychic" -> Color(0xFFF95587)   // Psychic - Pink
                "bug" -> Color(0xFFA6B91A)       // Bug - Lime Green
                "rock" -> Color(0xFFB6A136)      // Rock - Dark Yellow/Ochre
                "ghost" -> Color(0xFF735797)     // Ghost - Dark Purple/Indigo
                "dragon" -> Color(0xFF6F35FC)    // Dragon - Purple-Blue
                "dark" -> Color(0xFF705746)      // Dark - Dark Brown
                "steel" -> Color(0xFFB7B7CE)     // Steel - Light Steel Blue/Silver
                "fairy" -> Color(0xFFD685AD)     // Fairy - Pink/Mauve
                "stellar" -> Color(0xFFFFD700)   // Stellar - Gold
                "unknown" -> Color(0xFFBDBCBC)   // Unknown - Light Gray
                else -> Color(0xFF888888)        // Default - Neutral Gray for unmapped types
            }
        )
    }

    data class Header(
        val id: String,
        val name: String,
    ) {
        constructor(model: PokemonVO) : this(
            id = model.id.formatID(),
            name = model.name.capitalize()
        )
    }

    @JvmInline
    value class Type(
        val name: String,
    ) {
        constructor(model: TypeVO) : this(
            name = model.name.capitalize()
        )
    }

    data class ArtWork(
        val url: String,
        val contentDescription: String,
    ) {
        constructor(model: PokemonVO) : this(
            url = model.image,
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

    data class Move(
        val id: String,
        val name: String,
    ) {
        constructor(model: MoveVO) : this(
            id = model.id.formatID(),
            name = model.name.capitalize()
        )
    }
}

private fun String.capitalize() = this.capitalize(Locale.current)

private fun Float.formatPercent() = (this * 100).formatDecimal() + "%"

private fun Float.formatHeight() = (this * 10).formatDecimal() + " cm"

private fun Float.formatWeight() = (this / 10).formatDecimal() + " kg"

private fun Long.formatID() = "#" + this.toString().padStart(3, '0')

private fun List<String>.formatList() = this.joinToString(", ") { it.capitalize() }