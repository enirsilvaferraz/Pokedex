package com.eferraz.pokedex.database.core

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

internal val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pokemon_type1` ON `pokemon` (`type1`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pokemon_type2` ON `pokemon` (`type2`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pokemon_about` ON `pokemon` (`about`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pokemon_breeding` ON `pokemon` (`breeding`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pokemon_stats` ON `pokemon` (`stats`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_breeding_egg_groups_egg_group_id` ON `breeding_egg_groups` (`egg_group_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_pokemon_moves_move_id` ON `pokemon_moves` (`move_id`)")
    }
}
