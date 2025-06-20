package com.example.pokedex.list

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.PokemonVO
import com.example.pokedex.entity.Pokedex
import com.example.pokedex.network.PokedexDataSourceApi
import com.example.pokedex.network.PokemonSpeciesDataSourceApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val useCase: PokedexRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<SnapshotStateList<PokemonVO>>(SnapshotStateList())
    val pokemonList: StateFlow<SnapshotStateList<PokemonVO>> = _pokemonList.asStateFlow()

//    val pkm = useCase.get()

    init {
        viewModelScope.launch {
            useCase.get().onEach { delay(50) }.collect {
                _pokemonList.value.add(it)
            }
        }
    }

    private fun Pokedex.toVO(): List<PokemonVO> {
        return pokemonEntries.map { entry ->
            PokemonVO(
                id = entry.entryNumber.toInt(),
                color = Color.Red,
                name = entry.pokemonSpecies.name.orEmpty(),
                types = listOf()
            )
        }
    }
}

internal interface GetPokedexUseCase {
    operator fun invoke(): Flow<List<PokemonVO>>
}

internal interface PokedexRepository {
    fun get(): Flow<PokemonVO>
}


internal class PokedexRepositoryImpl(
    private val podekexDS: PokedexDataSourceApi,
    private val pokemonSpeciesDS: PokemonSpeciesDataSourceApi
) : PokedexRepository {

    override fun get(): Flow<PokemonVO> = flow {

        val pokedex = podekexDS.get()

        pokedex.pokemonEntries.forEach { entry ->

            val pokemonSpecies = pokemonSpeciesDS.get(entry.entryNumber)

            emit(
                PokemonVO(
                    id = pokemonSpecies.id,
                    color = pokemonSpecies.color.name.orEmpty().toColor(),
                    name = pokemonSpecies.name,
                    types = pokemonSpecies.eggGroups.map { it.name.orEmpty() }
                ))

//            delay(50)
        }
    }

    private fun String.toColor(): Color {
        return when (this) {
            "black" -> Color.Black
            "blue" -> Color.Blue
            "brown" -> Color.Blue //??
            "gray" -> Color.Gray
            "green" -> Color.Green
            "pink" -> Color.Red //??
            "purple" -> Color.Red //??
            "red" -> Color.Red
            "white" -> Color.White
            "yellow" -> Color.Yellow
            else -> Color.LightGray
        }
    }
}