package com.example.pokedex.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Returns a specific color associated with a given Pokemon type name.
 * These colors are chosen to be representative of the types in the Pokemon universe.
 *
 * @param typeName The name of the Pokemon type (e.g., "grass", "fire").
 * @return The Color associated with the type. Defaults to a neutral Gray if the type is not recognized.
 */
fun String.getColorForType(): Color {
    return when (this.lowercase()) {
        "normal" -> Color(0xFFA8A77A)    // Normal - Grayish Beige
        "fire" -> Color(0xFFEE8130)      // Fire - Orange-Red
        "water" -> Color(0xFF6390F0)     // Water - Blue
        "electric" -> Color(0xFFF7D02C)  // Electric - Yellow
        "grass" -> Color(0xFF7AC74C)     // Grass - Green
        "ice" -> Color(0xFF96D9D6)       // Ice - Light Cyan
        "fighting" -> Color(0xFFC22E28)  // Fighting - Dark Red
        "poison" -> Color(0xFFA33EA1)    // Poison - Purple
        "ground" -> Color(0xFFE2BF65)    // Ground - Light Brown
        "flying" -> Color(0xFFA98FF3)    // Flying - Lavender
        "psychic" -> Color(0xFFF95587)   // Psychic - Pink
        "bug" -> Color(0xFFA6B91A)       // Bug - Lime Green
        "rock" -> Color(0xFFB6A136)      // Rock - Dark Yellow/Ochre
        "ghost" -> Color(0xFF735797)     // Ghost - Dark Purple/Indigo
        "dragon" -> Color(0xFF6F35FC)    // Dragon - Purple-Blue
        "dark" -> Color(0xFF705746)      // Dark - Dark Brown
        "steel" -> Color(0xFFB7B7CE)     // Steel - Light Steel Blue/Silver
        "fairy" -> Color(0xFFD685AD)     // Fairy - Pink/Mauve
        "stellar" -> Color(0xFFFFD700)   // Stellar - Gold
        "unknown" -> Color(0xFFBDBCBC)   // Unknown - Light Gray
        else -> Color(0xFF888888)        // Default - Neutral Gray for unmapped types
    }
}
