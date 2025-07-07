package com.example.pokedex.ui

import androidx.lifecycle.ViewModel
import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class PokemonViewModel(
    id: Long,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(Loading)
    val state = _state.asStateFlow()

    init {
        _state.update { Success(pokemon) }
    }

    internal sealed interface State
    internal object Loading : State
    internal data class Success(val model: PokemonVO) : State
    internal object Error : State
}