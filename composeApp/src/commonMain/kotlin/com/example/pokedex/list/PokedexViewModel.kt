package com.example.pokedex.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.ExperimentalPagingApi
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.cachedIn
import com.example.repositories.PokemonRepository

internal class PokedexViewModel(
    private val repository: PokemonRepository,
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        config = PagingConfig(pageSize = 1, prefetchDistance = 10, initialLoadSize = 1),
        pagingSourceFactory = { repository },
    ).flow.cachedIn(viewModelScope)
}