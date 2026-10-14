package com.logue.frontend.data

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual fun httpEngine(): HttpClientEngine = Js.create()

actual val BASE_URL: String = "http://localhost:8080"
