package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.pokemon.DreamWorld
import com.eferraz.pokedex.network.responses.pokemon.Home
import com.eferraz.pokedex.network.responses.pokemon.OfficialArtwork
import com.eferraz.pokedex.network.responses.pokemon.ShowdownSprites
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OtherSprites(
    @SerialName("dream_world")
    val dreamWorld: DreamWorld?,
    @SerialName("home")
    val home: Home?,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork?,
    @SerialName("showdown")
    val showdown: ShowdownSprites?
)