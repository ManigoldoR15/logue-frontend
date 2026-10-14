package com.logue.frontend.data

import kotlinx.serialization.Serializable

// Deben coincidir con las data classes del backend: mismos nombres de campos.
@Serializable
enum class Rol { CIUDADANO, PARAMEDICO, ADMINISTRADOR }

@Serializable
data class LoginRequest(
    val usuario: String,
    val password: String,
)

@Serializable
data class LoginResponse(
    val ok: Boolean,
    val nombre: String? = null,
    val rol: Rol? = null,
    val mensaje: String,
)

// Datos de la sesión iniciada que usa la app después del login.
data class Sesion(
    val nombre: String,
    val rol: Rol,
)
