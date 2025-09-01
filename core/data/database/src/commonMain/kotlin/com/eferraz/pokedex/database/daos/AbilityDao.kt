package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import com.eferraz.pokedex.database.entities.AbilityTable

@Dao
internal interface AbilityDao : BaseDao<AbilityTable>
