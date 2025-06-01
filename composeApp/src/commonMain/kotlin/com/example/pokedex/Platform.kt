package com.example.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun formatId(id: Int): String