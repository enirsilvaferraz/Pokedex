package com.eferraz.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NamedApiResource(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String,
) {
    fun getId() = url.removeSuffix("/").substringAfterLast('/').toLong()
}