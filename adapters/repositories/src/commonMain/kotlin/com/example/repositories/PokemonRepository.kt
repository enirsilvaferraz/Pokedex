package com.example.repositories

import app.cash.paging.PagingSource
import com.example.pokedex.entity.PokemonVO

public abstract class PokemonRepository : PagingSource<Int, PokemonVO>()