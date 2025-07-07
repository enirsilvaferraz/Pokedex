package com.example.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.cachedIn
import com.example.pokedex.usecases.GetPokemonUseCase

internal class PokedexViewModel(
    useCase: GetPokemonUseCase,
) : ViewModel() {

    val flow = useCase().cachedIn(viewModelScope)
}