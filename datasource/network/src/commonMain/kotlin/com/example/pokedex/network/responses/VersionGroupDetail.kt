package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class VersionGroupDetail(
    @SerialName("level_learned_at")
    val levelLearnedAt: Int,
    @SerialName("move_learn_method")
    val moveLearnMethod: NamedApiResource,
    @SerialName("version_group")
    val versionGroup: NamedApiResource,
    @SerialName("order")
    val order: Int? = null,
)
