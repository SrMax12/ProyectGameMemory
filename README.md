<div align="center">

# 🧠 Memory Game

### Dos formas de jugar: en el navegador o desde el escritorio.

Encuentra todas las parejas, registra tus intentos y compite por un lugar en el ranking. El proyecto incluye una versión web y una aplicación de escritorio Java Swing; ambas pueden guardar resultados en MySQL.

<p>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white" alt="HTML5" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white" alt="CSS3" />
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black" alt="JavaScript" />
  <img src="https://img.shields.io/badge/PHP-777BB4?style=flat-square&logo=php&logoColor=white" alt="PHP" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Swing-437291?style=flat-square&logo=java&logoColor=white" alt="Java Swing" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white" alt="Maven" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white" alt="MySQL" />
</p>

</div>

---

## 📚 Contenido

- [Acerca del juego](#-acerca-del-juego)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos](#-requisitos)
- [Configuración de la base de datos](#️-configuración-de-la-base-de-datos)
- [Versión web](#-versión-web)
- [Aplicación de escritorio Java](#️-aplicación-de-escritorio-java)
- [Cómo jugar](#-cómo-jugar)
- [Estructura del proyecto](#-estructura-del-proyecto)

## 🎮 Acerca del juego

Memory Game es el clásico juego de encontrar parejas. El tablero tiene 16 cartas (ocho pares) que se mezclan al iniciar cada partida. El jugador debe descubrir todos los pares con la menor cantidad de intentos posible.

Al completar el tablero, se puede guardar el nombre, los intentos y el tiempo de la partida. Los resultados se almacenan en la tabla `ranking` de MySQL y cada versión muestra los cinco mejores puntajes.

## ✨ Características

- Versión web con HTML, CSS y JavaScript, servida mediante Apache y PHP.
- Aplicación de escritorio con interfaz Java Swing.
- Tablero aleatorio de 16 cartas y ocho parejas en ambas versiones.
- Contador de intentos, cronómetro y opción para reiniciar la partida.
- Guardado de nombre, intentos y tiempo en MySQL.
- Ranking Top 5 compartido a través de la misma base de datos.
- Operaciones de base de datos de la aplicación Swing ejecutadas en segundo plano para mantener la interfaz receptiva.

## 🛠️ Tecnologías

| Tecnología | Uso |
| --- | --- |
| HTML, CSS y JavaScript | Interfaz y lógica de la versión web |
| PHP | Acceso a MySQL desde la versión web |
| Java 8+ y Swing | Aplicación de escritorio |
| JDBC y MySQL Connector/J | Acceso a MySQL desde Java |
| Maven | Compilación y gestión de dependencias de Java |
| MySQL | Almacenamiento compartido de los puntajes |

## 📋 Requisitos

- **Versión web:** XAMPP (Apache, PHP y MySQL) o un entorno equivalente, más un navegador moderno.
- **Versión de escritorio:** JDK 8 o posterior, Maven y MySQL.
- **Base de datos:** la base `juegomemory` y la tabla `ranking` descritas abajo.

Maven descarga automáticamente MySQL Connector/J según la dependencia declarada en `pom.xml`.

## 🗄️ Configuración de la base de datos

Las dos versiones utilizan la misma base de datos. En phpMyAdmin o en tu cliente MySQL, crea `juegomemory` y ejecuta:

```sql
CREATE TABLE IF NOT EXISTS ranking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador VARCHAR(50) NOT NULL,
    intentos INT NOT NULL,
    tiempo_segundos INT NOT NULL DEFAULT 0,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Conexión desde la versión web

`guardar.php` y `obtener_ranking.php` usan por defecto `localhost`, base `juegomemory`, usuario `root` y contraseña vacía (configuración habitual de XAMPP). Si tus credenciales son diferentes, actualiza ambos archivos.

### Conexión desde la versión Java

La conexión predeterminada es `jdbc:mysql://localhost:3306/juegomemory`, usuario `root` y contraseña vacía. Puedes cambiarla mediante estas variables de entorno:

| Variable | Valor predeterminado |
| --- | --- |
| `MEMORY_DB_URL` | `jdbc:mysql://localhost:3306/juegomemory` |
| `MEMORY_DB_USER` | `root` |
| `MEMORY_DB_PASSWORD` | Vacío |

## 🌐 Versión web

1. Coloca el proyecto dentro de la carpeta `htdocs` de XAMPP, por ejemplo `C:\xampp\htdocs\memory`.
2. Inicia **Apache** y **MySQL**.
3. Configura la base de datos siguiendo la sección anterior.
4. Abre [http://localhost/memory/index.html](http://localhost/memory/index.html) en el navegador.

La lógica del juego web está en `index.html`. `guardar.php` recibe los resultados y `obtener_ranking.php` entrega el ranking en formato JSON.

## 🖥️ Aplicación de escritorio Java

Desde la raíz del proyecto, con JDK, Maven y MySQL disponibles, ejecuta:

```bash
mvn clean compile exec:java
```

El punto de entrada es `forms.FormMemory`. La lógica de acceso a datos se encuentra en `dao.RankingDAO`, y la conexión JDBC en `conexion.ConexionBD`. Si no puede guardar o cargar el ranking, la aplicación muestra un mensaje de error; la lógica de juego permanece disponible.

## 🕹️ Cómo jugar

1. Haz clic en dos cartas para revelar sus símbolos.
2. Si forman una pareja, quedarán resueltas; si no, volverán a ocultarse.
3. Encuentra las ocho parejas. El juego registra intentos y tiempo.
4. Al ganar, escribe tu nombre para guardar el resultado en el ranking.

## 📂 Estructura del proyecto

```text
memory/
├── index.html
├── guardar.php
├── obtener_ranking.php
├── pom.xml
├── src/
│   ├── conexion/ConexionBD.java
│   ├── dao/RankingDAO.java
│   ├── dominio/Carta.java
│   ├── dominio/Puntaje.java
│   └── forms/FormMemory.java
├── README.md
└── Tuto.txt
```

---

<div align="center">

Hecho como proyecto de práctica de desarrollo web y Java.

</div>
