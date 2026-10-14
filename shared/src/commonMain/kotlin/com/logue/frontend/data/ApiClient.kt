package com.logue.frontend.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// Crea el HttpClient de Ktor con ContentNegotiation + kotlinx.serialization (JSON).
fun createHttpClient(engine: HttpClientEngine = httpEngine()): HttpClient =
    HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
