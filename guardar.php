<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "juegomemory";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $jugador = $_POST['jugador'];
    $intentos = intval($_POST['intentos']);
    $tiempo = intval($_POST['tiempo']); // Recibe los segundos desde JS

    // Insertamos los 3 valores en la tabla
    $stmt = $conn->prepare("INSERT INTO ranking (jugador, intentos, tiempo_segundos) VALUES (?, ?, ?)");
    $stmt->bind_param("sii", $jugador, $intentos, $tiempo);
    
    if ($stmt->execute()) {
        echo "Éxito";
    } else {
        echo "Error: " . $conn->error;
    }
    
    $stmt->close();
}
$conn->close();
?>
