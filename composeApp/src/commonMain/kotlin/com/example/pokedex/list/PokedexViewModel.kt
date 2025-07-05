package com.example.pokedex.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokedex.entity.PokemonVO
import com.example.repositories.PokemonRepository
import com.example.repositories.datasources.PokemonDataSource
import com.example.repositories.di.PokemonPagingSource
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
    private val pokemonApi: PokemonDataSource.Network,
) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 1, prefetchDistance = 10, initialLoadSize = 1)
    ) {
        PokemonPagingSource(pokemonApi)
    }.flow
        .cachedIn(viewModelScope)

    private val _pokemonList = mutableStateListOf<PokemonVO>()
    val pokemonList: List<PokemonVO> = _pokemonList

//    init {
//        viewModelScope.launch {
//            useCase.getAll()?.onEach { delay(10) }?.collectLatest {
//                _pokemonList.addAll(it)
//            }
//        }
//
//        viewModelScope.launch(Dispatchers.IO) {
//            useCase.populateDatabase()
//        }
//    }
}