package com.example.pokedex

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.getColorForType

@Stable
data class PokemonVO(
    val id: Int,
    val name: String,
    val types: List<String>,
    val url: String// = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
) {
    fun formatedId() = formatId(id)

    fun color() = types.first().getColorForType()
}