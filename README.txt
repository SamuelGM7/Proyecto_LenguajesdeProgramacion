===============================
RECOMENDADOR DE PELÍCULAS
===============================

Autor: Samuel
Lenguajes: Java + Prolog
Librerías: JPL (Java-Prolog Bridge), Swing (interfaz gráfica)
Java requerido: Java 17 o superior
Prolog requerido: SWI-Prolog

-------------------------------
📁 ESTRUCTURA DEL PROYECTO
-------------------------------

Proyecto/
├── src/
│   └── Main.java             --> Interfaz Swing y conexión con Prolog
├── Prolog/
│   └── peliculas.pl          --> Base de datos y lógica de recomendación en Prolog
├── lib/
│   └── jpl.jar               --> Librería puente entre Java y Prolog (JPL)
├── bin/
│   └── (compilados .class)   --> Archivos generados al compilar

-------------------------------
⚙️ CÓMO COMPILAR
-------------------------------

1. Abre PowerShell o CMD en la carpeta raíz del proyecto
2. Ejecuta el siguiente comando para compilar:

    javac -cp "lib/jpl.jar;src" -d bin src/Main.java

Esto genera los archivos `.class` dentro de la carpeta `bin`.

-------------------------------
▶️ CÓMO EJECUTAR
-------------------------------

1. Asegúrate de tener SWI-Prolog instalado.
2. Verifica que jpl.dll esté en: 
   C:/Program Files/swipl/bin (y esa ruta esté en tu PATH)
3. Ejecuta este comando desde la carpeta raíz del proyecto:

    java --enable-native-access=ALL-UNNAMED -cp "bin;lib/jpl.jar" ^
         "-Djava.library.path=C:/Program Files/swipl/bin" Main

⚠️ En PowerShell o CMD usa `^` para dividir líneas. O todo en una sola línea si prefieres.

-------------------------------
🧠 FUNCIONAMIENTO
-------------------------------

- La app abre una ventana gráfica con filtros: Género, Duración, Idioma, Clasificación, Plataforma.
- Al hacer clic en "Buscar películas":
    - Java recoge los filtros seleccionados
    - Transforma las listas al formato de Prolog
    - Llama a la regla: recomendar_pelicula(...) definida en `peliculas.pl`
    - Se muestran las películas recomendadas en una ventana emergente

-------------------------------
🛠️ DEPURACIÓN (DEBUG)
-------------------------------

- En consola se imprimen los filtros seleccionados y la consulta Prolog generada.
- Si no aparecen resultados:
    - Verifica la tilde en "Más de 120 min" (usa `normalizar`)
    - Prueba la consulta directamente en SWI-Prolog
    - Asegúrate de que `peliculas.pl` esté bien cargado

-------------------------------
💡 EJEMPLO DE CONSULTA PROLOG
-------------------------------

Desde Prolog puedes probar:

    ?- consult('Prolog/peliculas.pl').
    ?- recomendar_pelicula(T, ['accion'], ['espanol'], '90 - 120 min', 'mayores_13', 'netflix').

-------------------------------
📌 NOTAS FINALES
-------------------------------

- `.stream()` permite transformar listas en Java fácilmente
- `.map(s -> "'" + s + "'")` convierte los elementos en átomos Prolog válidos
- `.toList()` vuelve a convertir el flujo en una lista usable
- No pongas comillas manualmente en la GUI; agrégalas solo en la consulta

¡Proyecto listo para ser presentado o extendido! 🎬

