package com.eferraz.pokedex.database.daos

import androidx.room3.Dao
import com.eferraz.pokedex.database.entities.StatsTable

@Dao
internal interface StatsDao : BaseDao<StatsTable>
