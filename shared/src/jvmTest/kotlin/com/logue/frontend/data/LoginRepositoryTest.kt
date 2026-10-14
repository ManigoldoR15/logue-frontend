package com.logue.frontend.data

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// Prueba el repositorio sin backend real: MockEngine simula las respuestas HTTP.
class LoginRepositoryTest {

    private fun repositorio(status: HttpStatusCode, json: String) = LoginRepository(
        client = createHttpClient(
            MockEngine { respond(json, status, headersOf(HttpHeaders.ContentType, "application/json")) },
        ),
        baseUrl = "http://test",
    )

    @Test
    fun loginCorrectoDeserializaRol() = runBlocking {
        val repo = repositorio(
            HttpStatusCode.OK,
            """{"ok":true,"nombre":"Juan Pérez","rol":"CIUDADANO","mensaje":"Bienvenido"}""",
        )
        val respuesta = repo.login("ciudadano1", "1234")
        assertTrue(respuesta.ok)
        assertEquals(Rol.CIUDADANO, respuesta.rol)
    }

    @Test
    fun login401DevuelveMensajeDelServidor() = runBlocking {
        val repo = repositorio(
            HttpStatusCode.Unauthorized,
            """{"ok":false,"nombre":null,"rol":null,"mensaje":"Usuario o contraseña incorrectos"}""",
        )
        val respuesta = repo.login("admin1", "mal")
        assertFalse(respuesta.ok)
        assertEquals("Usuario o contraseña incorrectos", respuesta.mensaje)
    }
}
