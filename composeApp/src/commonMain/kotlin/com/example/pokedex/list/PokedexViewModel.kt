package com.example.pokedex.list

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.pokedex.PokemonVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PokedexViewModel(
    private val useCase: GetPokedexUseCase
) : ViewModel() {

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
                            Color.Companion.Green,
                            Color.Companion.Green,
                            Color.Companion.Green,
                            Color.Companion.Red,
                            Color.Companion.Red,
                            Color.Companion.Red,
                            Color.Companion.Blue,
                            Color.Companion.Blue,
                            Color.Companion.Blue,
                            Color.Companion.Green
                        ).getOrNull(it) ?: Color.Companion.LightGray,
                        id = it + 1,
                        name = "Blastoise",
                        types = listOf("Type 1", "Type 2")
                    )
                )
            }
        }
    }
}

internal interface GetPokedexUseCase {
    operator fun invoke(): Flow<List<PokemonVO>>
}

internal interface PokedexRepository {
    fun get(): Flow<List<PokemonVO>>
}