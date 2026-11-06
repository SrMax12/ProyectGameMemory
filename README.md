<div align="center">

# 🧠 Memory Game

### Encuentra todas las parejas y supera tu mejor puntuación.

Un juego de memoria para navegador, construido con HTML, CSS, JavaScript, PHP y MySQL.

<p>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white" alt="HTML5" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white" alt="CSS3" />
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black" alt="JavaScript" />
  <img src="https://img.shields.io/badge/PHP-777BB4?style=flat-square&logo=php&logoColor=white" alt="PHP" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white" alt="MySQL" />
</p>

</div>

---

## 📚 Contenido

- [Acerca del juego](#-acerca-del-juego)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Instalación](#-instalación)
- [Cómo jugar](#-cómo-jugar)
- [Estructura](#-estructura-del-proyecto)
- [Configuración de la base de datos](#️-configuración-de-la-base-de-datos)

## 🎮 Acerca del juego

Memory Game es el clásico juego de encontrar parejas. Las cartas se mezclan al iniciar una partida y el jugador debe descubrir los pares con la menor cantidad de intentos posible.

Al completar el tablero, se puede guardar el nombre, los intentos y el tiempo de la partida. Los resultados se almacenan en MySQL y el juego muestra un ranking de hasta cinco jugadores, ordenado por intentos.

## ✨ Características

- Tablero de 16 cartas con 8 parejas.
- Mezcla aleatoria de cartas en cada partida.
- Contador de intentos y cronómetro.
- Opción para reiniciar o volver a jugar.
- Guardado de resultados en MySQL.
- Ranking de los cinco puntajes con menos intentos.

## 🛠️ Tecnologías

| Tecnología | Uso |
| --- | --- |
| HTML y CSS | Interfaz y estilos del juego |
| JavaScript | Lógica de las cartas, intentos, tiempo y ranking |
| PHP | Comunicación con la base de datos |
| MySQL | Almacenamiento de los puntajes |

## 🚀 Instalación

### Requisitos

- XAMPP, con Apache y MySQL.
- Un navegador web moderno.

### Pasos

1. Coloca la carpeta del proyecto dentro de `htdocs`:

   ```text
   C:\xampp\htdocs\memory
   ```

2. Inicia los servicios **Apache** y **MySQL** desde el panel de XAMPP.

3. Entra a [phpMyAdmin](http://localhost/phpmyadmin/) y crea una base de datos llamada `juegomemory`.

4. En la base de datos, abre la pestaña **SQL** y ejecuta la consulta de [configuración de la base de datos](#️-configuración-de-la-base-de-datos).

5. Abre el juego en el navegador:

   [http://localhost/memory/index.html](http://localhost/memory/index.html)

## 🕹️ Cómo jugar

1. Haz clic en dos cartas para revelar sus símbolos.
2. Si forman una pareja, quedarán resueltas; si no, volverán a ocultarse.
3. Encuentra las ocho parejas. El juego registra los intentos y el tiempo.
4. Al ganar, escribe tu nombre para guardar el resultado en el ranking.

## 📂 Estructura del proyecto

```text
memory/
├── index.html          # Interfaz y lógica del juego
├── guardar.php         # Guarda un resultado en MySQL
├── obtener_ranking.php # Devuelve el ranking en formato JSON
├── README.md           # Documentación del proyecto
└── Tuto.txt            # Guía de despliegue en XAMPP
```

## 🗄️ Configuración de la base de datos

En phpMyAdmin, selecciona `juegomemory`, abre la pestaña **SQL** y ejecuta:

```sql
CREATE TABLE IF NOT EXISTS ranking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador VARCHAR(50) NOT NULL,
    intentos INT NOT NULL,
    tiempo_segundos INT NOT NULL DEFAULT 0,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

La conexión PHP está configurada por defecto para XAMPP:

| Parámetro | Valor |
| --- | --- |
| Servidor | `localhost` |
| Base de datos | `juegomemory` |
| Usuario | `root` |
| Contraseña | Vacía |

Si tu instalación de MySQL usa otros datos, actualiza la configuración de conexión en `guardar.php` y `obtener_ranking.php`.

---

<div align="center">

Hecho como proyecto de práctica de desarrollo web.

</div>
