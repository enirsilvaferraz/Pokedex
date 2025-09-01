package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.NamedApiResource
import com.eferraz.pokedex.network.responses.pokemon.VersionGroupDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MoveEntry(
    @SerialName("move")
    val move: NamedApiResource,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)