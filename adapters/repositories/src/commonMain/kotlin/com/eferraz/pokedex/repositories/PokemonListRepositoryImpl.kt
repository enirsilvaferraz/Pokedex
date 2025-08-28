package com.eferraz.pokedex.repositories

import app.cash.paging.PagingState
import com.eferraz.pokedex.entity.PokemonLightVO
import com.eferraz.pokedex.repositories.datasources.PokemonDataSource
import com.eferraz.pokedex.usecases.repositories.PokemonListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided

@Factory(binds = [PokemonListRepository::class])
internal class PokemonListRepositoryImpl(
    @Provided private val api: PokemonDataSource.Network,
    @Provided private val database: PokemonDataSource.Database,
) : PokemonListRepository() {

    override fun getRefreshKey(state: PagingState<Int, PokemonLightVO>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonLightVO> {

        val currentPage = params.key ?: 1
        val limit = params.loadSize

        val pokemonList = fetchData(limit = limit, currentPage = currentPage)

        val prevKey = if (currentPage == 1) null else currentPage - 1
        val nextKey = (currentPage + 1).takeIf { it <= 151 }

        return LoadResult.Page(data = pokemonList, prevKey = prevKey, nextKey = nextKey)
    }

    private suspend fun fetchData(currentPage: Int, limit: Int): List<PokemonLightVO> = withContext(Dispatchers.IO) {

        val offset = (currentPage - 1) * limit

        iterateOffset(limit, offset) { id ->
            database.getLight(id) ?: api.get(id).also { database.insert(it) }.toLight()
        }
    }

    private suspend fun <T> iterateOffset(limit: Int, offset: Int, callback: suspend (Int) -> T): List<T> {

        val start = offset + 1
        val end = limit + start

        return (start until end).mapNotNull { id ->
            callback(id)
        }
    }
}