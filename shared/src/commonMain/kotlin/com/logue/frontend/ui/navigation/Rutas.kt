package com.logue.frontend.ui.navigation

import com.logue.frontend.data.Rol
import kotlinx.serialization.Serializable

// Rutas de navegación "type-safe": también usan @Serializable.
@Serializable object LoginRoute
@Serializable object CiudadanoRoute
@Serializable object EmergenciaRoute
@Serializable object ParamedicoRoute
@Serializable object AdministradorRoute

// Aquí se decide a qué vista va cada rol después del login.
fun rutaPorRol(rol: Rol): Any = when (rol) {
    Rol.CIUDADANO -> CiudadanoRoute
    Rol.PARAMEDICO -> ParamedicoRoute
    Rol.ADMINISTRADOR -> AdministradorRoute
}
