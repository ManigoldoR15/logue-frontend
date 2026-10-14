package com.logue.frontend.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val CruzRojaRojo = Color(0xFFC8102E)
val CruzRojaRojoOscuro = Color(0xFF8E0B20)
val GrisFondo = Color(0xFFF7F2F2)

private val LogueColors = lightColorScheme(
    primary = CruzRojaRojo,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDAD9),
    onPrimaryContainer = CruzRojaRojoOscuro,
    secondary = Color(0xFF5D5F5F),
    onSecondary = Color.White,
    background = GrisFondo,
    surface = Color.White,
    error = Color(0xFFB3261E),
    errorContainer = Color(0xFFFFE1DE),
    onErrorContainer = Color(0xFF7A1410),
)

@Composable
fun LogueTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LogueColors, content = content)
}
