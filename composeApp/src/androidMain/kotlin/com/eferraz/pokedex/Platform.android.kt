package com.eferraz.pokedex

import java.util.Locale

actual fun formatId(id: Long) = String.format(Locale.getDefault(), "#%03d", id)