package com.example.pokedex

import androidx.compose.ui.graphics.Color

data class PokemonVO(
    private val id: Int,
    val color: Color = Color.Companion.LightGray,
    val name: String,
    val types: List<String> = emptyList(),
    val url: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
) {
    fun formatedId() = formatId(id)
}