package com.eferraz.pokedex

internal actual fun formatId(id: Long) = "#${id.toString().padStart(3, '0')}"