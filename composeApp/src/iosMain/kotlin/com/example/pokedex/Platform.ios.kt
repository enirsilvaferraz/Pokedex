package com.example.pokedex

actual fun formatId(id: Long) = "#${id.toString().padStart(3, '0')}"