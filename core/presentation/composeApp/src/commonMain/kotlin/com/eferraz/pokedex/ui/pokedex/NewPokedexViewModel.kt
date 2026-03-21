package com.eferraz.pokedex.ui.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.entity.summary.PokemonPlaceholder
import com.eferraz.pokedex.usecases.GetPokemonSummaryUseCase
import com.eferraz.pokedex.usecases.NewObservePokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
internal class NewPokedexViewModel(
    @Provided private val observePokemonList: NewObservePokemonListUseCase,
    @Provided private val getSummary: GetPokemonSummaryUseCase,
) : ViewModel() {

    val state: StateFlow<UiState> field = MutableStateFlow<UiState>(UiState.Loading)

    init {
        dispatch(Intent.InitialLoad)
    }

    internal fun dispatch(intent: Intent) = when (intent) {
        Intent.InitialLoad -> onInitLoad()
        Intent.Retry -> onRetry() // todo testar isso
        is Intent.ItemVisible -> onItemVisible(intent)
    }

    private fun onInitLoad() {
        observePokemonList()
            .onEach { items ->
                state.value = UiState.Success(summaries = items.associateBy { it.id })
            }
            .catch {
                state.value = UiState.Error
            }
            .launchIn(viewModelScope)
    }

    private fun onRetry() {
        state.value = UiState.Loading
        onInitLoad()
    }

    private fun onItemVisible(intent: Intent.ItemVisible) {
        (intent.pokemon as? PokemonPlaceholder)?.let { placeholder: PokemonPlaceholder ->
            viewModelScope.launch {
                getSummary(placeholder)
            }
        }
    }

    sealed interface UiState {
        data object Loading : UiState
        data object Error : UiState
        data class Success(val summaries: Map<Long, BasePokemon>) : UiState
    }

    sealed interface Intent {
        data object InitialLoad : Intent
        data object Retry : Intent
        data class ItemVisible(val pokemon: BasePokemon) : Intent
    }
}
