package com.eferraz.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.cachedIn
import com.eferraz.pokedex.usecases.GetPokemonUseCase
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
internal class PokedexViewModel(
    @Provided useCase: GetPokemonUseCase,
) : ViewModel() {

    val flow = useCase().cachedIn(viewModelScope)
}