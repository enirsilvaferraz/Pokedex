package com.example.repositories

import app.cash.paging.PagingSource
import com.example.pokedex.entity.PokemonVO

abstract class PokemonRepository : PagingSource<Int, PokemonVO>()