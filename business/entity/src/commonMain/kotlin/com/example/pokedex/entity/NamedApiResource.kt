package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamedApiResource(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String
)