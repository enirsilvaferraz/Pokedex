package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.NamedApiResource
import com.eferraz.pokedex.network.responses.pokemon.VersionDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class HeldItem(
    @SerialName("item")
    val item: NamedApiResource,
    @SerialName("version_details")
    val versionDetails: List<VersionDetail>
)