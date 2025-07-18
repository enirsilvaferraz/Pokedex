package com.eferraz.pokedex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.repositories.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PokemonViewModel(
    id: Long,
    useCase: PokemonRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                _state.update { Success(PokemonView(useCase.get(id))) }
            }.getOrElse {
                _state.update { Error }
            }
        }
    }

    internal sealed interface State
    internal object Loading : State
    internal data class Success(val vo: PokemonView) : State
    internal object Error : State
}