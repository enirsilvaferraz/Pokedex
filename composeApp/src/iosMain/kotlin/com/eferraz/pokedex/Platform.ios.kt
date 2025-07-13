package com.eferraz.pokedex

actual fun formatId(id: Long) = "#${id.toString().padStart(3, '0')}"