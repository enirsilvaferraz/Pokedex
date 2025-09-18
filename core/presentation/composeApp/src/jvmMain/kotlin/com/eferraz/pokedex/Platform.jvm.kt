package com.eferraz.pokedex

import java.util.Locale

@Deprecated(message = "Migrando para uma versao multiplataforma")
internal actual fun formatId(id: Long): String = String.format(Locale.getDefault(), "#%03d", id)