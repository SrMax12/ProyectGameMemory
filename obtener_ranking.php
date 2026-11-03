<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "juegomemory";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$sql = "SELECT jugador, intentos FROM ranking ORDER BY intentos ASC LIMIT 5";
$result = $conn->query($sql);

$ranking = [];
if ($result && $result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $ranking[] = $row;
    }
}

header('Content-Type: application/json');
echo json_encode($ranking);

$conn->close();
?>
