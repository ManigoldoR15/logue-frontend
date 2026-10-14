package com.logue.frontend.data

import io.ktor.client.engine.HttpClientEngine

// expect: cada plataforma da su propio "actual" (CIO, OkHttp o Js).
expect fun httpEngine(): HttpClientEngine

// URL del backend. En el emulador de Android "localhost" es el propio emulador.
expect val BASE_URL: String
