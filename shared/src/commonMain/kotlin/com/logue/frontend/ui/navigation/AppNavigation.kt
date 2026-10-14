package com.logue.frontend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.logue.frontend.ui.screens.AdministradorScreen
import com.logue.frontend.ui.screens.CiudadanoScreen
import com.logue.frontend.ui.screens.EmergenciaScreen
import com.logue.frontend.ui.screens.LoginScreen
import com.logue.frontend.ui.screens.ParamedicoScreen
import com.logue.frontend.viewmodel.LoginViewModel

@Composable
fun AppNavigation(
    viewModel: LoginViewModel = viewModel { LoginViewModel() },
) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Cuando aparece una sesión, navegamos según el rol y quitamos Login del historial.
    LaunchedEffect(state.sesion) {
        val sesion = state.sesion ?: return@LaunchedEffect
        navController.navigate(rutaPorRol(sesion.rol)) {
            popUpTo<LoginRoute> { inclusive = true }
        }
    }

    val cerrarSesion: () -> Unit = {
        viewModel.logout()
        navController.navigate(LoginRoute) {
            popUpTo(navController.graph.id) { inclusive = true }
        }
    }
    val nombre = state.sesion?.nombre.orEmpty()

    NavHost(navController = navController, startDestination = LoginRoute) {
        composable<LoginRoute> {
            LoginScreen(
                state = state,
                onUsuarioChange = viewModel::onUsuarioChange,
                onPasswordChange = viewModel::onPasswordChange,
                onLogin = viewModel::login,
            )
        }
        composable<CiudadanoRoute> {
            CiudadanoScreen(
                nombre = nombre,
                onSosActivado = { navController.navigate(EmergenciaRoute) },
                onCerrarSesion = cerrarSesion,
            )
        }
        composable<EmergenciaRoute> {
            EmergenciaScreen(
                nombre = nombre,
                onVolver = { navController.popBackStack() },
                onCerrarSesion = cerrarSesion,
            )
        }
        composable<ParamedicoRoute> {
            ParamedicoScreen(nombre = nombre, onCerrarSesion = cerrarSesion)
        }
        composable<AdministradorRoute> {
            AdministradorScreen(nombre = nombre, onCerrarSesion = cerrarSesion)
        }
    }
}
