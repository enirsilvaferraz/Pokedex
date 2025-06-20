package com.example.pokedex.list

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.PokemonVO
import com.example.pokedex.entity.Pokedex
import com.example.pokedex.network.PokedexDataSource
import com.example.pokedex.network.PokedexDataSourceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val useCase: PokedexDataSourceApi
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonVO>>(emptyList())
    val pokemonList: StateFlow<List<PokemonVO>> = _pokemonList

    init {
        viewModelScope.launch {
            _pokemonList.value = useCase.getAll().toVO()
        }
    }

    private fun Pokedex.toVO() : List<PokemonVO> {
        return pokemonEntries.map { entry -> PokemonVO(
            id = entry.entryNumber.toInt(),
            color = Color.Red,
            name = entry.pokemonSpecies.name,
            types = listOf()
        ) }
    }
}

internal interface GetPokedexUseCase {
    operator fun invoke(): Flow<List<PokemonVO>>
}

internal interface PokedexRepository {
    fun get(): Flow<List<PokemonVO>>
}