package com.example.pokedex.list

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.PokemonVO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val useCase: PokedexRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<SnapshotStateList<PokemonVO>>(SnapshotStateList())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        viewModelScope.launch {
            useCase.get().onEach { delay(0) }.collect {
                _pokemonList.value.add(it)
            }
        }
    }
}