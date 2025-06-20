package com.example.pokedex

import androidx.compose.ui.graphics.Color

data class PokemonVO(
    val id: Int,
    val color: Color = Color.Companion.LightGray,
    val name: String,
    val types: List<String> = emptyList(),
    val url: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
) {
    fun formatedId() = formatId(id)

    companion object {
        fun Empty() = PokemonVO(id = 0, name = "")
    }
}