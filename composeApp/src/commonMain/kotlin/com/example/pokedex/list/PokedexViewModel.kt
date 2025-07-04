package com.example.pokedex.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.entity.PokemonVO
import com.example.repositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val useCase: PokemonRepository,
) : ViewModel() {

    private val _pokemonList = mutableStateListOf<PokemonVO>()
    val pokemonList: List<PokemonVO> = _pokemonList

    init {
        viewModelScope.launch {
            useCase.getAll()?.onEach { delay(10) }?.collectLatest {
                _pokemonList.addAll(it)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            useCase.populateDatabase()
        }
    }
}