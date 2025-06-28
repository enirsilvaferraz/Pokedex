package com.example.pokedex.database.core

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.component.inject

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal actual object PlatformDataBaseBuilder : DataBaseBuilder {

    val context: Context by inject()

    actual override fun buildPlatform(): RoomDatabase.Builder<AppDatabase> {

        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath("pokedex.db")

        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath,
        )
    }
}