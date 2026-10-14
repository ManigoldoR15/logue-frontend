# Logue · Frontend (Kotlin Multiplatform + Compose Multiplatform)

Demo de app de emergencias para la **Cruz Roja Mexicana**. Este repositorio es la **app** (Android, Desktop y Web);
el servidor está en [logue-backend](https://github.com/ManigoldoR15/logue-backend).

Generado con el [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com).

## Arquitectura

Arquitectura local con backend y frontend **separados**. El frontend sigue el patrón
**UI → ViewModel → Repositorio → Ktor Client → backend**:

```
 ui/ (Compose)          viewmodel/                data/                         backend
 LoginScreen ──login()──▶ LoginViewModel ──────▶ LoginRepository ──POST /login──▶ Ktor :8080
     ▲                     StateFlow<LoginUiState>   HttpClient + ContentNegotiation   users.json
     │                            │
 AppNavigation ◀── sesion.rol ────┘   when(rol) → Ciudadano | Paramédico | Administrador
```

| Rol | Vista |
|---|---|
| `CIUDADANO` | Botón **SOS** (mantener 5 s, con barra de progreso) → 4 opciones: disparos, accidente vial, persona inconsciente, múltiples heridos |
| `PARAMEDICO` | Lista de incidentes de ejemplo con tipo, ubicación y ETA |
| `ADMINISTRADOR` | Panel con resumen: incidentes activos y unidades disponibles (datos de ejemplo) |

Cada vista tiene botón **Cerrar sesión**. Tema rojo Cruz Roja (`#C8102E`).

## Estructura

```
frontend/
├── shared/                      # Código compartido (aquí está casi toda la app)
│   └── src/
│       ├── commonMain/kotlin/com/logue/frontend/
│       │   ├── App.kt                    # Punto de entrada común: LogueTheme + AppNavigation
│       │   ├── data/
│       │   │   ├── Models.kt             # Rol, LoginRequest, LoginResponse (@Serializable), Sesion
│       │   │   ├── HttpEngine.kt         # expect: engine HTTP y BASE_URL por plataforma
│       │   │   ├── ApiClient.kt          # HttpClient con ContentNegotiation + kotlinx.serialization
│       │   │   ├── LoginRepository.kt    # POST /login
│       │   │   └── DatosEjemplo.kt       # Incidentes y unidades de ejemplo
│       │   ├── viewmodel/
│       │   │   └── LoginViewModel.kt     # ViewModel + StateFlow<LoginUiState>
│       │   └── ui/
│       │       ├── theme/Theme.kt        # Colores Cruz Roja
│       │       ├── navigation/Rutas.kt   # Rutas @Serializable y when(rol)
│       │       ├── navigation/AppNavigation.kt  # NavHost
│       │       └── screens/              # Login, Ciudadano, Emergencia, Paramedico, Administrador, LogueTopBar
│       ├── jvmMain/.../data/HttpEngine.jvm.kt          # actual: CIO   + http://localhost:8080
│       ├── androidMain/.../data/HttpEngine.android.kt  # actual: OkHttp + http://10.0.2.2:8080
│       ├── webMain/.../data/HttpEngine.web.kt          # actual: Js    + http://localhost:8080
│       └── jvmTest/.../data/LoginRepositoryTest.kt     # Pruebas con MockEngine
├── desktopApp/   # main() de escritorio: abre una ventana con App()
├── androidApp/   # MainActivity: setContent { App() }
└── webApp/       # main() web: ComposeViewport { App() }
```

### Engine HTTP por plataforma

| Plataforma | Engine | URL base | Por qué |
|---|---|---|---|
| Desktop (JVM) | CIO | `http://localhost:8080` | Engine puro de Kotlin para la JVM |
| Android | OkHttp | `http://10.0.2.2:8080` | En el emulador, `10.0.2.2` es la PC (`localhost` sería el propio emulador) |
| Web (JS / Wasm) | Js | `http://localhost:8080` | Usa `fetch` del navegador; requiere CORS en el backend |

## Cómo correr

Requiere JDK 21 y el **backend corriendo** en el puerto 8080 (`./gradlew run` en `logue-backend`).
Si tu `java` por defecto es otra versión, indica JDK 21 solo para el comando:

```bash
# Desktop (la plataforma probada)
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 ./gradlew :desktopApp:run

# Pruebas de escritorio
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 ./gradlew :shared:jvmTest
```

Otras plataformas (comandos del wizard; no probadas en esta demo):

- Android: `./gradlew :androidApp:assembleDebug` (requiere Android SDK en `local.properties`)
- Web Wasm: `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
- Web JS: `./gradlew :webApp:jsBrowserDevelopmentRun`

Usuarios de prueba: `ciudadano1`, `paramedico1`, `admin1`, todos con contraseña `1234`.
