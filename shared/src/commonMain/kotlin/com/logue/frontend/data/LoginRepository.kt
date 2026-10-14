package com.logue.frontend.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

// Único lugar que habla con el backend. El ViewModel no sabe nada de HTTP.
class LoginRepository(
    private val client: HttpClient = createHttpClient(),
    private val baseUrl: String = BASE_URL,
) {
    suspend fun login(usuario: String, password: String): LoginResponse =
        try {
            client.post("$baseUrl/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(usuario, password))
            }.body<LoginResponse>() // 200 y 401 traen el mismo JSON LoginResponse
        } catch (e: Exception) {
            LoginResponse(
                ok = false,
                mensaje = "No se pudo conectar con el servidor ($baseUrl)",
            )
        }
}
