# JCMaterialApp
Aplicacion Android basada en Jetpack Compose. Este repositorio es un fork/adaptacion de un proyecto existente, por lo que la documentacion se enfoca en la estructura actual y en los cambios realizados para llevar la interfaz hacia Material 3.

## Objetivo del fork

El objetivo principal de esta version es modernizar la experiencia visual y de componentes usando Material 3, manteniendo una arquitectura simple para fines academicos. La app presenta un formulario de registro con campos reutilizables, seleccion de fecha, perfiles, validaciones basicas, pantalla de procesamiento y dialogo de confirmacion.

## Arquitectura del proyecto

El proyecto esta organizado como una aplicacion Android de un solo modulo:

- `app/`: modulo principal de la aplicacion Android.
- `MainActivity.kt`: punto de entrada. Configura `JCMaterialAppTheme`, `Scaffold` y la pantalla principal.
- `screens/`: pantallas Compose de alto nivel, actualmente centradas en `MainView`.
- `components/`: componentes reutilizables de UI como campos de texto, date picker, dialogos, radio buttons y pantalla de procesamiento.
- `model/`: modelos simples de dominio, como `User`.
- `utils/`: funciones auxiliares para formateo y reglas de formulario.
- `fake_data/`: datos o delays simulados para representar estados de carga/procesamiento.
- `ui/theme/`: definicion visual de Material 3: colores, tipografia, formas y tema general.
- `res/`: recursos Android como strings, dimensiones, iconos, fuentes y configuracion XML.

La logica se mantiene cerca de la UI porque el alcance del proyecto es educativo y reducido. Para una aplicacion de produccion, podria separarse en capas mas estrictas como `data`, `domain` y `presentation`, ademas de incorporar ViewModels.

## Adaptacion a Material 3

La edicion de este fork pone enfasis en reemplazar y ajustar la UI hacia Material 3:

- Uso de `androidx.compose.material3` como base de componentes visuales.
- Tema centralizado en `JCMaterialAppTheme` con `MaterialTheme`.
- Soporte para color dinamico en Android 12 o superior mediante `dynamicLightColorScheme` y `dynamicDarkColorScheme`.
- Paleta clara personalizada con tonos `DeepOrange`, `Green`, `Yellow` y superficies propias.
- Formas personalizadas en `Shape.kt` usando `CutCornerShape` para dar identidad visual a botones, cards y campos.
- Tipografia personalizada en `Type.kt`, incluyendo fuentes locales desde `res/font`.
- Componentes Compose reutilizables adaptados al estilo Material 3, como `CustomTextField`, `CustomDateTextField`, `CustomDialog` y `RadioButtonProfiles`.
- Uso de `Scaffold`, `Card`, `Button`, `Switch`, `Icon` y otros componentes Material 3 para estructurar la pantalla.

## Requisitos

- Android Studio compatible con Kotlin y Jetpack Compose.
- JDK 11.
- Gradle Wrapper incluido en el repositorio.
- SDK Android configurado para compilar con `compileSdk 36`.

## Ejecucion

Desde Android Studio:

1. Abrir el proyecto.
2. Sincronizar Gradle.
3. Ejecutar el modulo `app` en un emulador o dispositivo.

Desde terminal:

```bash
./gradlew assembleDebug
```

## Uso

Este proyecto se distribuye con fines exclusivamente estudiantiles. Su uso comercial, venta, reventa o integracion en productos comerciales no esta permitido. Ver el archivo `LICENSE` para mas detalles.
