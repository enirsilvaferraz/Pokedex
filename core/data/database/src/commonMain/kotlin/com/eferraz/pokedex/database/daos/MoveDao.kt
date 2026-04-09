package com.eferraz.pokedex.database.daos

import androidx.room3.Dao
import com.eferraz.pokedex.database.entities.MoveTable

@Dao
internal interface MoveDao : BaseDao<MoveTable>
