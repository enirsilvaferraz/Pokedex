package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MoveEntry(
    @SerialName("move")
    val move: NamedApiResource,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)
