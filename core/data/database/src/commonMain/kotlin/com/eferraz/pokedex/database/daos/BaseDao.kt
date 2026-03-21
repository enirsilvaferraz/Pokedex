package com.eferraz.pokedex.database.daos

import androidx.room.Upsert

internal interface BaseDao<T : Any> {

    @Upsert
    suspend fun upsert(vararg entity: T)
}