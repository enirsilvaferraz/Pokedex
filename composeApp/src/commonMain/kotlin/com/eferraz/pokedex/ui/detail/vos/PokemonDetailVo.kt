package com.eferraz.pokedex.ui.detail.vos

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.helpers.format
import com.eferraz.pokedex.helpers.getColorForType
import org.jetbrains.compose.resources.DrawableResource
import kotlin.jvm.JvmInline

internal typealias FieldValueVo = Pair<String, String>

internal data class PokemonDetailVo(
    val background: Background,
    val header: Header,
    val types: Types,
    val image: ImageVo,
    val description: String,
    val about: About,
    val breeding: Breeding,
    val stats: Stats,
    val moves: Moves,
) {

    constructor(model: PokemonCompleteVO) : this(
        background = Background(model),
        header = Header(model),
        types = Types(model),
        image = ImageVo.Remote(model),
        description = model.about.description,
        about = About(model),
        breeding = Breeding(model),
        stats = Stats(model),
        moves = Moves(model)
    )

    /**
     * Background area
     */
    @JvmInline
    value class Background(val color: Color) {
        constructor(model: PokemonVO) : this(
            color = model.type1.getColorForType()
        )
    }

    /**
     * Header area
     */
    data class Header(val id: String, val name: String) {
        constructor(model: PokemonVO) : this(
            id = "#" + model.id.toString().padStart(3, '0'),
            name = model.name.capitalize(Locale.Companion.current)
        )
    }

    /**
     * Type area
     */
    @JvmInline
    value class Types(val types: List<String>) {
        constructor(model: PokemonVO) : this(
            types = model.types().map { it.name.capitalize(Locale.Companion.current) }
        )
    }

    /**
     * Image area
     */
    internal sealed interface ImageVo {

        val contentDescription: String

        data class Remote(val url: String, override val contentDescription: String) : ImageVo {
            constructor(model: PokemonVO) : this(
                url = model.image,
                contentDescription = model.name
            )
        }

        data class Local(val resource: DrawableResource, override val contentDescription: String) : ImageVo
    }


    /**
     * About area
     */
    @JvmInline
    value class About(val items: List<FieldValueVo>) {
        constructor(model: PokemonCompleteVO) : this(
            items = listOf(
                FieldValueVo("Species", model.about.species.capitalize(Locale.Companion.current)),
                FieldValueVo("Category", model.about.category.capitalize(Locale.Companion.current)),
                FieldValueVo("Height", (model.about.height / 10).format(Locale.current.platformLocale) + " cm"),
                FieldValueVo("Weight", (model.about.weight / 10).format(Locale.current.platformLocale) + " kg"),
                FieldValueVo("Abilities", model.about.abilities.joinToString(","))
            )
        )
    }

    /**
     * Breeding area
     */
    @JvmInline
    value class Breeding(val items: List<FieldValueVo>) {
        constructor(model: PokemonVO) : this(
            items = listOf(
                FieldValueVo("Gender", "87,5% Male, 12,5% Female"),
                FieldValueVo("Egg Groups", "Monster"),
                FieldValueVo("Egg Cycle", "Grass")
            )
        )
    }

    /**
     * Stats area
     */
    @JvmInline
    value class Stats(val items: List<ChartItem>) {

        constructor(model: PokemonVO) : this(
            items = listOf(
                ChartItem("HP", 45),
                ChartItem("Attack", 49),
                ChartItem("Defense", 49),
                ChartItem("Sp. Atk", 65),
                ChartItem("Sp. Def", 65),
                ChartItem("Speed", 45),
                ChartItem("Total", 318, max = 600)
            )
        )

        internal data class ChartItem(
            val field: String,
            val value: Int,
            private val max: Int = 100,
        ) {

            fun text() = value.toString()

            fun progress() = value.toFloat() / max.toFloat()

            fun color() = if (value > max / 2) Color.Companion.Green else Color.Companion.Red
        }
    }

    /**
     * Abilities area
     */
    @JvmInline
    value class Moves(val items: List<FieldValueVo>) {
        constructor(model: PokemonCompleteVO) : this(
            items = model.moves.map { FieldValueVo(it.id.toString(), it.name.capitalize(Locale.current)) }
        )
    }
}