package com.example.repositories


import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.PokedexDataSource
import com.example.repositories.datasources.PokemonDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

internal class PokemonRepositoryImpl(
//    private val readableDataSource: List<ReadableDataSource<PokemonVO>>,
//    private val writableDataSource: List<WritableDataSource<PokemonVO>>,
    private val pokedexApi: PokedexDataSource.Network,
    private val pokemonApi: PokemonDataSource.Network,
    private val pokemonDb: PokemonDataSource.Database,
) : PokemonRepository {

    override suspend fun getAll(): Flow<List<PokemonVO>> {
        return pokemonDb.getAll()
    }

    override suspend fun populateDatabase() {

        pokemonDb.getAll().collect {
            if (it.isEmpty()) {
                pokedexApi.get(2).map { it.pokemon }.collect {
                    pokemonDb.insert(it)
                }
            } else {
                it.filter { it.type1 == null }.forEach {
                    pokemonApi.get(it.id).collect {
                        pokemonDb.update(it)
                    }
                }
            }
        }
    }
}
