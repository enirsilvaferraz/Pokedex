package com.eferraz.pokedex.database.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy

internal interface BaseDao<T : Any> {

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(vararg entity: T)
}