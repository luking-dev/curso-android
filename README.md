# Curso Android

## Clases grabadas
| Nro de clase | Enlace |
| ------------ | ------ |
| Clase 1 | https://youtu.be/7LvlhdG66zI |
| Clase 2 | https://youtu.be/V-8_7Vxbil4 |
| Clase 3 | https://youtu.be/-QfXOjKQiBI |
| Clase 4 | https://youtu.be/4QjwnAFuQrI |

## Instalacion

* [IntelliJ IDEA (Community)](https://www.jetbrains.com/es-es/idea/download/) (elegir Community, del lado derecho de la pagina web).
* [Android Studio](https://developer.android.com/studio).

Si tenes otra arquitectura de procesador o el instalador anterior no funciona, probablemente tengas que descargar la version x86 desde [aca](https://redirector.gvt1.com/edgedl/android/studio/ide-zips/3.6.3.0/android-studio-ide-192.6392135-windows32.zip).

## Prerequisitos
Instalar [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html#license-lightbox).

## Procesadores Intel
Puede que se necesite configurar el [acelerador de graficos](https://developer.android.com/studio/run/emulator-acceleration?hl=es-419#avd-gpu).

## Procesadores AMD
Para aquellos que cuenten con un procesador AMD, deberan:
1. Corroborar que Hyper-V este deshabilitado. [Como lo hago?](https://translate.google.com/translate?sl=en&tl=es&u=https://github.com/google/android-emulator-hypervisor-driver-for-amd-processors/wiki/Is-Hyper-V-really-disabled%253F)
2. Activar el modo SVM en la BIOS. [Como lo hago?](https://concamilo.com/solucion-android-emulator-hypervisor-driver-for-amd-processors-installation-failed/)
3. Instalar el [Driver del Emulador de Android](https://github.com/google/android-emulator-hypervisor-driver-for-amd-processors/releases/download/v1.7/gvm-windows_v1_7_0.zip). [Como lo hago?](https://translate.google.com/translate?hl=&sl=en&tl=es&u=https%3A%2F%2Fgithub.com%2Fgoogle%2Fandroid-emulator-hypervisor-driver-for-amd-processors%2Fblob%2Fmaster%2FREADME.md)

## Detectar procesador y tipo de arquitectura
Podes averiguarlo consultando [esta web](https://www.genbeta.com/windows/como-saber-que-procesador-tiene-mi-pc?utm_source=feedburner&utm_medium=feed&utm_campaign=Feed:+genbeta+(Genbeta)) y siguiendo las instrucciones.

## Primeros pasos
Iniciar Android Studio.

Crear un nuevo proyecto. Para ello:

```sh
File > New > New Project > Empty Activity > En el campo Name dar un nombre a la aplicacion > Finnish
```

## Encriptar contraseñas
[Aqui](https://www.samclarke.com/kotlin-hash-strings/) hay una breve explicacion.
