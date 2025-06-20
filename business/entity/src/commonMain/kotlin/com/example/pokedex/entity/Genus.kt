package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genus(
    @SerialName("genus")
    val genus: String,
    @SerialName("language")
    val language: NamedApiResource
)