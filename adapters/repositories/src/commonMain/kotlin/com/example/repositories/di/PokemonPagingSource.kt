package com.example.repositories.di

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.PokemonDataSource

class PokemonPagingSource(
    private val pokemonApi: PokemonDataSource.Network,
) : PagingSource<Int, PokemonVO>() {

    protected suspend fun fetchData(currentPage: Int, limit: Int): List<PokemonVO> {
        val offset = (currentPage - 1) * limit + 1
        return pokemonApi.get(limit = limit, offset = offset)
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonVO>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.

//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }

        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonVO> {

        val currentPage = params.key ?: 1
        val limit = params.loadSize

        val pokemonList = fetchData(limit = limit, currentPage = currentPage)

        val prevKey = if (currentPage == 1) null else currentPage - 1
        val nextKey = (currentPage + 1).takeIf { it <= 500 }

        return LoadResult.Page(
            data = pokemonList,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}