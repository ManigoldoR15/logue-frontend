package com.logue.frontend.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

// Barra superior común: título, nombre del usuario y botón de cerrar sesión.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogueTopBar(titulo: String, nombre: String, onCerrarSesion: () -> Unit) {
    TopAppBar(
        title = {
            Column {
                Text(titulo, fontWeight = FontWeight.Bold)
                if (nombre.isNotEmpty()) {
                    Text(nombre, style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        actions = {
            TextButton(onClick = onCerrarSesion) {
                Text("Cerrar sesión", color = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}
