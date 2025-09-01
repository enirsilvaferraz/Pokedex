package com.eferraz.pokedex.network.responses.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GameSprites(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_gray")
    val backGray: String?,
    @SerialName("back_shiny")
    val backShiny: String?,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,
    @SerialName("back_shiny_transparent")
    val backShinyTransparent: String?,
    @SerialName("back_transparent")
    val backTransparent: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_gray")
    val frontGray: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?,
    @SerialName("front_shiny_transparent")
    val frontShinyTransparent: String?,
    @SerialName("front_transparent")
    val frontTransparent: String?,
    @SerialName("animated")
    val animated: ShowdownSprites? // Reusing ShowdownSprites for animated as structure is similar
)