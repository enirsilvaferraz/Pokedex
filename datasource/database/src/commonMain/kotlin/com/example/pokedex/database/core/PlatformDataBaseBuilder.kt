package com.example.pokedex.database.core

import androidx.room.RoomDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal expect object PlatformDataBaseBuilder : DataBaseBuilder {

    override fun buildPlatform(): RoomDatabase.Builder<AppDatabase>
}