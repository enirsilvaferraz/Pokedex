package com.eferraz.pokedex.database.core

import androidx.room.RoomDatabaseConstructor

@Suppress("KotlinNoActualForExpect")
internal expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}