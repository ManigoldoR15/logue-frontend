package com.logue.frontend.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val tiposEmergencia = listOf(
    "Disparos",
    "Accidente vial",
    "Persona inconsciente",
    "Múltiples heridos",
)

@Composable
fun EmergenciaScreen(
    nombre: String,
    onVolver: () -> Unit,
    onCerrarSesion: () -> Unit,
) {
    var seleccion by remember { mutableStateOf<String?>(null) }

    Scaffold(topBar = { LogueTopBar("SOS activado", nombre, onCerrarSesion) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val enviado = seleccion
            if (enviado == null) {
                Text(
                    "¿Qué tipo de emergencia es?",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                tiposEmergencia.forEach { tipo ->
                    Button(
                        onClick = { seleccion = tipo },
                        modifier = Modifier.widthIn(max = 400.dp).fillMaxWidth().height(64.dp),
                    ) {
                        Text(tipo, style = MaterialTheme.typography.titleMedium)
                    }
                }
                OutlinedButton(onClick = onVolver) { Text("Cancelar") }
            } else {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.widthIn(max = 400.dp).fillMaxWidth(),
                ) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Alerta enviada", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("Tipo: $enviado")
                        Text("Una unidad de Cruz Roja va en camino. Mantén la calma.")
                    }
                }
                OutlinedButton(onClick = onVolver) { Text("Volver al inicio") }
            }
        }
    }
}
