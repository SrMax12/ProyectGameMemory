# 🧠 Memory Game

<p align="center">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3" />
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript" />
  <img src="https://img.shields.io/badge/PHP-777BB4?style=for-the-badge&logo=php&logoColor=white" alt="PHP" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
</p>

Un juego clásico de memoria desarrollado con HTML, CSS, JavaScript y PHP, con un ranking persistente en MySQL.

## 🎮 ¿Qué hace?

- Baraja cartas de forma aleatoria
- Mide el tiempo de la partida
- Cuenta los intentos del jugador
- Detecta coincidencias y finaliza la partida
- Guarda el mejor puntaje en una base de datos
- Muestra el Top 5 de mejores jugadores

## 📁 Estructura del proyecto

```text
memory/
├── index.html
├── guardar.php
├── obtener_ranking.php
├── README.md
├── Tuto.txt
└── ...
```

## 🚀 Requisitos

- XAMPP o cualquier entorno con Apache + MySQL + PHP
- Navegador web moderno
- Acceso al panel de administración de MySQL

## ⚙️ Instalación

1. Guarda la carpeta del proyecto dentro de `htdocs`:

```text
C:\xampp\htdocs\memory
```

2. Inicia Apache y MySQL desde el panel de XAMPP.

3. Abre phpMyAdmin en:

```text
http://localhost/phpmyadmin/
```

4. Crea una base de datos llamada:

```sql
juegomemory
```

5. Ejecuta esta estructura SQL:

```sql
CREATE TABLE IF NOT EXISTS ranking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador VARCHAR(50) NOT NULL,
    intentos INT NOT NULL,
    tiempo_segundos INT NOT NULL DEFAULT 0,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## ▶️ Cómo jugar

Abre esta URL en tu navegador:

```text
http://localhost/memory/index.html
```

Cuando termines la partida, escribe tu nombre para guardar tu puntuación y ver cómo quedas en el ranking.

## 🧩 Archivos principales

### `index.html`
Contiene la interfaz del juego, la lógica del tablero, el contador de intentos y el cronómetro.

### `guardar.php`
Recibe el nombre del jugador, la cantidad de intentos y el tiempo, y los guarda en la tabla `ranking`.

### `obtener_ranking.php`
Consulta los mejores puntajes y devuelve los resultados en formato JSON para cargar el ranking en la página.

## 📌 Nota importante

El proyecto usa la configuración por defecto de XAMPP:

- usuario: `root`
- contraseña: vacía
- base de datos: `juegomemory`

Si cambias la configuración de MySQL, deberás ajustar los valores de conexión en PHP.

## 🪪 Licencia

Proyecto educativo y de práctica libre para aprender desarrollo web con frontend y backend básico.

---
