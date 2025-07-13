package com.eferraz.repositories

import app.cash.paging.PagingSource
import com.eferraz.pokedex.entity.PokemonVO

public abstract class PokemonListRepository : PagingSource<Int, PokemonVO>()