package com.eferraz.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class HeldItem(
    @SerialName("item")
    val item: NamedApiResource,
    @SerialName("version_details")
    val versionDetails: List<VersionDetail>
)
