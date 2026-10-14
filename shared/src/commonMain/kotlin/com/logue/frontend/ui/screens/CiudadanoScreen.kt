package com.logue.frontend.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.logue.frontend.ui.theme.CruzRojaRojoOscuro
import kotlin.math.ceil

private const val DURACION_SOS_MS = 5_000

@Composable
fun CiudadanoScreen(
    nombre: String,
    onSosActivado: () -> Unit,
    onCerrarSesion: () -> Unit,
) {
    var presionando by remember { mutableStateOf(false) }
    val progreso = remember { Animatable(0f) }

    // Mientras se mantiene presionado, la barra avanza de 0 a 1 en 5 s.
    // Si se suelta antes, este efecto se cancela y la barra vuelve a 0.
    LaunchedEffect(presionando) {
        if (presionando) {
            progreso.animateTo(1f, tween(DURACION_SOS_MS, easing = LinearEasing))
            onSosActivado()
        } else {
            progreso.snapTo(0f)
        }
    }

    val segundosRestantes = ceil((1f - progreso.value) * DURACION_SOS_MS / 1000f).toInt()

    Scaffold(topBar = { LogueTopBar("Ciudadano", nombre, onCerrarSesion) }) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        ) {
            Text(
                "¿Tienes una emergencia?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "Mantén presionado el botón SOS durante 5 segundos",
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(200.dp)
                    .scale(if (presionando) 0.95f else 1f)
                    .clip(CircleShape)
                    .background(if (presionando) CruzRojaRojoOscuro else MaterialTheme.colorScheme.primary)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                presionando = true
                                tryAwaitRelease() // espera a que se suelte
                                presionando = false
                            },
                        )
                    },
            ) {
                Text("SOS", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Black)
            }

            LinearProgressIndicator(
                progress = { progreso.value },
                modifier = Modifier.widthIn(max = 320.dp).fillMaxWidth().height(10.dp),
            )
            Text(
                if (presionando) "Sigue presionando… $segundosRestantes s" else "Suelta para cancelar",
                color = if (presionando) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}
