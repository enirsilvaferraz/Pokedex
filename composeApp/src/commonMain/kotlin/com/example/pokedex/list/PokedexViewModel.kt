package com.example.pokedex.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.repositories.PokemonRepository
import com.example.repositories.datasources.PokemonDataSource
import com.example.repositories.di.PokemonPagingSource

internal class PokedexViewModel(
    private val useCase: PokemonRepository,
    private val pokemonApi: PokemonDataSource.Network,
) : ViewModel() {

    val flow = Pager(
        config = PagingConfig(pageSize = 1, prefetchDistance = 10, initialLoadSize = 1),
        pagingSourceFactory = { PokemonPagingSource(pokemonApi) }
    ).flow.cachedIn(viewModelScope)
}