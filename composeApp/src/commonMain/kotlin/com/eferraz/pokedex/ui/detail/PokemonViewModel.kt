package com.eferraz.pokedex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.pokedex.ui.detail.vos.PokemonDetailVo
import com.eferraz.repositories.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Provided

@KoinViewModel
internal class PokemonViewModel(
    @InjectedParam private val id: Long,
    @Provided private val useCase: PokemonRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                _state.update { Success(PokemonDetailVo(useCase.get(id))) }
            }.getOrElse {
                _state.update { Error }
            }
        }
    }

    internal sealed interface State
    internal object Loading : State
    internal data class Success(val vo: PokemonDetailVo) : State
    internal object Error : State
}