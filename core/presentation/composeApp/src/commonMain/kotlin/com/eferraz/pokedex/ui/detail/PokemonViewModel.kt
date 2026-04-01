package com.eferraz.pokedex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.pokedex.core.params.PokemonSummaryParam
import com.eferraz.pokedex.ui.detail.data.PokemonDetailDataView
import com.eferraz.pokedex.ui.detail.data.PokemonDetailStructureView
import com.eferraz.pokedex.usecases.GetPokemonDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
internal class PokemonViewModel(
    @InjectedParam private val ref: PokemonSummaryParam,
    @Provided private val useCase: GetPokemonDetailsUseCase,
) : ViewModel() {

    val structure: PokemonDetailStructureView by lazy { PokemonDetailStructureView(ref) }

    val state: StateFlow<State> field = MutableStateFlow<State>(State.Loading(structure))

    init {
        dispatch(Intent.InitialLoad)
    }

    internal fun dispatch(intent: Intent) = when (intent) {
        Intent.InitialLoad -> executeLoad()
        Intent.Retry -> {
            state.update { State.Loading(structure) }
            executeLoad()
        }
    }

    private fun executeLoad() {

        viewModelScope.launch {

            useCase(GetPokemonDetailsUseCase.Params(ref.id))
                .onSuccess { detailed ->
                    state.update { State.Success(structure, PokemonDetailDataView(detailed)) }
                }
                .onFailure {
                    state.update { State.Error(structure) }
                }
        }
    }

    internal sealed interface State {

        val structure: PokemonDetailStructureView

        data class Loading(override val structure: PokemonDetailStructureView) : State
        data class Success(override val structure: PokemonDetailStructureView, val data: PokemonDetailDataView) : State
        data class Error(override val structure: PokemonDetailStructureView) : State
    }

    internal sealed interface Intent {
        data object InitialLoad : Intent
        data object Retry : Intent
    }
}
