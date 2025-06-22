package com.example.pokedex

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal object AppRoutes {

    @Serializable
    object Pokedex

    @Serializable
    data class Pokemon(@SerialName("id") val id: Int)
}