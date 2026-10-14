package com.logue.frontend.data

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

actual fun httpEngine(): HttpClientEngine = CIO.create()

actual val BASE_URL: String = "http://localhost:8080"
