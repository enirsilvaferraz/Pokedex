package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationSprites(
    @SerialName("red-blue")
    val redBlue: GameSprites?,
    @SerialName("yellow")
    val yellow: GameSprites?,
    @SerialName("crystal")
    val crystal: GameSprites?,
    @SerialName("gold")
    val gold: GameSprites?,
    @SerialName("silver")
    val silver: GameSprites?,
    @SerialName("emerald")
    val emerald: GameSprites?,
    @SerialName("firered-leafgreen")
    val fireredLeafgreen: GameSprites?,
    @SerialName("ruby-sapphire")
    val rubySapphire: GameSprites?,
    @SerialName("diamond-pearl")
    val diamondPearl: GameSprites?,
    @SerialName("heartgold-soulsilver")
    val heartgoldSoulsilver: GameSprites?,
    @SerialName("platinum")
    val platinum: GameSprites?,
    @SerialName("black-white")
    val blackWhite: GameSprites?,
    @SerialName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: GameSprites?,
    @SerialName("x-y")
    val xY: GameSprites?,
    @SerialName("icons")
    val icons: GameSprites?,
    @SerialName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: GameSprites?
)
