package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveEntry(
    @SerialName("move")
    val move: NamedApiResource,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)
