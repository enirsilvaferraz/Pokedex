package com.example.pokedex

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokemonViewModel: ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonVO>>(emptyList())
    val pokemonList: StateFlow<List<PokemonVO>> = _pokemonList

    init {
        _pokemonList.value = getPokemonList()
    }

    private fun getPokemonList(): List<PokemonVO> {
        return arrayListOf<PokemonVO>().apply {
            repeat(151) {
                add(
                    PokemonVO(
                        color = listOf(
                            Color.Green,
                            Color.Green,
                            Color.Green,
                            Color.Red,
                            Color.Red,
                            Color.Red,
                            Color.Blue,
                            Color.Blue,
                            Color.Blue,
                            Color.Green
                        ).getOrNull(it) ?: Color.LightGray,
                        id = it + 1,
                        name = "Blastoise",
                        types = listOf("Type 1", "Type 2")
                    )
                )
            }
        }
    }
}