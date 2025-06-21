package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class VersionSprites(
    @SerialName("generation-i")
    val generationI: GenerationSprites?,
    @SerialName("generation-ii")
    val generationIi: GenerationSprites?,
    @SerialName("generation-iii")
    val generationIii: GenerationSprites?,
    @SerialName("generation-iv")
    val generationIv: GenerationSprites?,
    @SerialName("generation-v")
    val generationV: GenerationSprites?,
    @SerialName("generation-vi")
    val generationVi: GenerationSprites?,
    @SerialName("generation-vii")
    val generationVii: GenerationSprites?,
    @SerialName("generation-viii")
    val generationViii: GenerationSprites?
)
