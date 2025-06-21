package com.example.pokedex.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val useCase: PokedexRepository
) : ViewModel() {

    private val _pokemonList = mutableStateListOf<PokemonVO>()
    val pokemonList: List<PokemonVO> = _pokemonList

    init {
        viewModelScope.launch {
            useCase.get().collect {
                _pokemonList.add(it)
            }
        }
    }
}