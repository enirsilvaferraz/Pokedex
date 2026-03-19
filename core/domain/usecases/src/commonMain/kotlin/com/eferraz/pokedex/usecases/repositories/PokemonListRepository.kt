package com.eferraz.pokedex.usecases.repositories

import app.cash.paging.PagingSource
import com.eferraz.pokedex.entity.PokemonLight

public abstract class PokemonListRepository : PagingSource<Int, PokemonLight>()