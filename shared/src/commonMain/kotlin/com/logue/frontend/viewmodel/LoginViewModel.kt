package com.logue.frontend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logue.frontend.data.LoginRepository
import com.logue.frontend.data.Sesion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Todo lo que la pantalla necesita dibujar, en un solo objeto inmutable.
data class LoginUiState(
    val usuario: String = "",
    val password: String = "",
    val cargando: Boolean = false,
    val error: String? = null,
    val sesion: Sesion? = null,
)

class LoginViewModel(
    private val repository: LoginRepository = LoginRepository(),
) : ViewModel() {

    // Privado y mutable adentro; público y de solo lectura afuera.
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun onUsuarioChange(valor: String) = _state.update { it.copy(usuario = valor, error = null) }

    fun onPasswordChange(valor: String) = _state.update { it.copy(password = valor, error = null) }

    fun login() {
        val actual = _state.value
        if (actual.usuario.isBlank() || actual.password.isBlank()) {
            _state.update { it.copy(error = "Escribe usuario y contraseña") }
            return
        }
        _state.update { it.copy(cargando = true, error = null) }

        viewModelScope.launch {
            val respuesta = repository.login(actual.usuario.trim(), actual.password)
            val nombre = respuesta.nombre
            val rol = respuesta.rol
            _state.update {
                if (respuesta.ok && nombre != null && rol != null) {
                    it.copy(cargando = false, sesion = Sesion(nombre, rol))
                } else {
                    it.copy(cargando = false, error = respuesta.mensaje)
                }
            }
        }
    }

    fun logout() {
        _state.value = LoginUiState()
    }
}
