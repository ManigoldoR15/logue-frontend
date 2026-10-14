package com.logue.frontend.data

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun httpEngine(): HttpClientEngine = OkHttp.create()

// 10.0.2.2 es la dirección con la que el emulador de Android ve a la PC.
actual val BASE_URL: String = "http://10.0.2.2:8080"
