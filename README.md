# Curso Android

## Instalacion

* [IntelliJ IDEA (Community)](https://www.jetbrains.com/es-es/idea/download/) (elegir Community, del lado derecho de la pagina web)
* [Android Studio](https://developer.android.com/studio)

Si tenes otra arquitectura de procesador o el instalador anterior no funciona, probablemente tengas que descargar la version x86 desde [aca](https://redirector.gvt1.com/edgedl/android/studio/ide-zips/3.6.3.0/android-studio-ide-192.6392135-windows32.zip).


## Procesadores AMD
Para aquellos que cuenten con un procesador AMD, deberan:
1. Corroborar que Hyper-V este deshabilitado. [Como lo hago?](https://translate.google.com/translate?sl=en&tl=es&u=https://github.com/google/android-emulator-hypervisor-driver-for-amd-processors/wiki/Is-Hyper-V-really-disabled%253F)
2. Activar el modo SVM en la BIOS. [Como lo hago?](https://concamilo.com/solucion-android-emulator-hypervisor-driver-for-amd-processors-installation-failed/)
3. Instalar el [Driver del Emulador de Android](https://github.com/google/android-emulator-hypervisor-driver-for-amd-processors/releases/download/v1.7/gvm-windows_v1_7_0.zip). [Como lo hago?](https://translate.google.com/translate?hl=&sl=en&tl=es&u=https%3A%2F%2Fgithub.com%2Fgoogle%2Fandroid-emulator-hypervisor-driver-for-amd-processors%2Fblob%2Fmaster%2FREADME.md)

## Primeros pasos
Iniciar Android Studio.

Crear un nuevo proyecto. Para ello:

_File > New > New Project > Empty Activity > En el campo Name dar un nombre a la aplicacion > Finnish_
