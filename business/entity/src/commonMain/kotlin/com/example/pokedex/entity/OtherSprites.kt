package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherSprites(
    @SerialName("dream_world")
    val dreamWorld: DreamWorld?,
    @SerialName("home")
    val home: Home?,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork?,
    @SerialName("showdown")
    val showdown: ShowdownSprites?
)
