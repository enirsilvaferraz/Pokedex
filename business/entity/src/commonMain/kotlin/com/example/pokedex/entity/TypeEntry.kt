package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeEntry(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: NamedApiResource
)
