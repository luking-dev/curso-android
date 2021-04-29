# Curso Android

## Clases grabadas
| # de clase | Enlace | # de clase | Enlace |
| ---------- | ------ | ---------- | ------ |
| Clase 1 | https://youtu.be/7LvlhdG66zI | Clase 6 | https://youtu.be/A2dZuJSjYq4 |
| Clase 2 | https://youtu.be/V-8_7Vxbil4 | Clase 7 | https://youtu.be/qWSvusqXTY8 |
| Clase 3 | https://youtu.be/-QfXOjKQiBI | Clase 8 | https://youtu.be/PJmwsgZbCuM |
| Clase 4 | https://youtu.be/4QjwnAFuQrI | Clase 9 | https://youtu.be/m-Zg7VheyI4 |
| Clase 5 | https://youtu.be/CfXRE5R2Im0 | Clase 10 | https://youtu.be/Bqp-36ettrI |

## Instalacion

* [IntelliJ IDEA (Community)](https://www.jetbrains.com/es-es/idea/download/) (elegir Community, del lado derecho de la pagina web).
* [Android Studio](https://developer.android.com/studio).

Si tenes otra arquitectura de procesador o el instalador anterior no funciona, probablemente tengas que descargar la version x86 desde [aca](https://redirector.gvt1.com/edgedl/android/studio/ide-zips/3.6.3.0/android-studio-ide-192.6392135-windows32.zip).

## Pre-requisitos
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

Crear una unidad virtual. Para ello, dirigirse al menu:

```sh
Tools > AVD Manager > Create Virtual Device > Phone > Nexus 5X
```

## Encriptar contraseñas
[Aqui](https://www.samclarke.com/kotlin-hash-strings/) encontraremos el algoritmo que usaremos en nuestro IDE de programacion.

## Generador de hash SHA-1
Podemos encriptar una cadena de caracteres utilizando el algoritmo SHA-1 con ayuda de [esta](https://passwordsgenerator.net/sha1-hash-generator/) herramienta online.

## Gestionar permisos
Agregar los permisos requeridos dentro de la estructura:

```sh
app > manifests > AndroidManifest.xml
```

```kotlin
<uses-permission android:name="android.permission.INTERNET" />
```

## API Rest
Servicio Web para ejemplos de API Rest: https://reqres.in/

## Documentacion de Android
Toda las referencias al momento del desarrollo se puede encontrar en la [pagina oficial](https://developer.android.com/) de Android Studio.

## Android Asynchronous Http Client
Herramienta para realizar [peticiones http asincronicas](https://loopj.com/android-async-http/) para consumir servicios web.

## Dependencias externas
Para instalar herramientas de terceros como la mencionada arriba, primero se la debe importar:

```sh
Gradle Scripts > build.gradle (Module) > dependencies
```

Luego, dentro del objeto dependencies, escribir la siguiente linea:

```kotlin
dependencies {
    ...
    implementation 'com.loopj.android:android-async-http:1.4.9'
    ...
}
```

## Evitar cacheo de App (***no testeado***)
Es conveniente eliminar los datos de cache de la aplicacion para no arrastrar errores al momento de modificar codigo y que los cambios se vean reflejados en la compilacion de testeo.

La manera mas sencilla de realizar esto con menor intervencion de codigo es utilizando [Apache Commons IO](https://commons.apache.org/proper/commons-io/) o alguna otra API que realice esto mismo en su lugar.

Importar la utilidad que acabamos de mencionar, y llamar al metodo ```deleteQuietly()```, pasando ```context.getCacheDir()``` como parametro:

```kotlin
import org.apache.commons.io.FileUtils;

...

// Eliminar el directorio local de cache (ignorando cualquier error)
FileUtils.deleteQuietly(context.getCacheDir());
```

_Nota: Si lo utiliza, tambien se debe eliminar el directorio devuelto por ```context.getExternalCacheDir()```._

Para poder usar Apache Commons IO, recordar agregar lo siguiente al archivo ```build.gradle``` que hace referencia al modulo, en la parte de dependencias:

```kotlin
compile 'commons-io:commons-io:2.4'
```

Referencia tomada de https://stackoverflow.com/a/43092909

## Objetos JSON
Los servicios web por lo general devuelven respuestas en formato JSON. Se puede mejorar la lectura de dichos objetos con [esta](https://codebeautify.org/jsonviewer) herramienta.

## Libreria para manejo de imagenes
[Documentacion](https://square.github.io/picasso/) de la herramienta.

## Lenguaje SQL
[Referencia](https://www.w3schools.com/sql) del lenguaje SQL.

## Visor de Bases de Datos SQLite
Se puede utilizar [esta](https://sqlitebrowser.org/dl/) herramienta para ver el contenido de Bases de Datos SQLite.

## Generacion del archivo APK de la aplicacion
Para generar el archivo final para distribuir la aplicacion, debemos dirigirnos al menu:

```sh
Build > Build Bundle(s) / APK(s) > Build APK
```

[Aqui](https://developer.android.com/guide/app-bundle?utm_source=android-studio), una explicacion de la diferencia entre APKs y Bundles.

## Herramienta para crear APIs
Podemos hacerlo a traves de [esta](https://www.mockapi.io/) pagina web.
