package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import com.eferraz.pokedex.database.entities.MoveTable

@Dao
internal interface MoveDao : BaseDao<MoveTable>
