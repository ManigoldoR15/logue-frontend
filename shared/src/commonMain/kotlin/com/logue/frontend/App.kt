package com.logue.frontend

import androidx.compose.runtime.Composable
import com.logue.frontend.ui.navigation.AppNavigation
import com.logue.frontend.ui.theme.LogueTheme

// Punto de entrada común: lo llaman Android, Desktop y Web.
@Composable
fun App() {
    LogueTheme {
        AppNavigation()
    }
}
