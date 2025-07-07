package com.example.repositories

import app.cash.paging.PagingState
import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.PokemonDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class PokemonRepositoryImpl(
    private val api: PokemonDataSource.Network,
    private val database: PokemonDataSource.Database,
) : PokemonRepository() {

    override fun getRefreshKey(state: PagingState<Int, PokemonVO>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonVO> {

        val currentPage = params.key ?: 1
        val limit = params.loadSize

        val pokemonList = fetchData(limit = limit, currentPage = currentPage)

        val prevKey = if (currentPage == 1) null else currentPage - 1
        val nextKey = (currentPage + 1).takeIf { it <= 151 }

        return LoadResult.Page(data = pokemonList, prevKey = prevKey, nextKey = nextKey)
    }

    private suspend fun fetchData(currentPage: Int, limit: Int): List<PokemonVO> = withContext(Dispatchers.IO) {

        val offset = (currentPage - 1) * limit

        iterateOffset(limit, offset) { id ->
            database.get(id) ?: api.get(id).also { database.insert(it) }
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