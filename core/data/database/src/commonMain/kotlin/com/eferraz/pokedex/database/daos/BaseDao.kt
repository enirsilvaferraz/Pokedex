package com.eferraz.pokedex.database.daos

import androidx.room3.Upsert

internal interface BaseDao<T : Any> {

    @Upsert
    suspend fun upsert(vararg entity: T)
}
