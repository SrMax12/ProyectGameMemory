package dao;

import conexion.ConexionBD;
import dominio.Puntaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {

    public void guardarRecord(String jugador, int intentos, int tiempo) throws SQLException {
        if (jugador == null || jugador.trim().isEmpty() || jugador.trim().length() > 50) {
            throw new IllegalArgumentException("El nombre debe tener entre 1 y 50 caracteres.");
        }
        if (intentos < 1 || tiempo < 0) {
            throw new IllegalArgumentException("Los intentos deben ser al menos uno y el tiempo no puede ser negativo.");
        }

        String sql = "INSERT INTO ranking (jugador, intentos, tiempo_segundos) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, jugador.trim());
            ps.setInt(2, intentos);
            ps.setInt(3, tiempo);
            ps.executeUpdate();
        }
    }

    public List<Puntaje> obtenerTopRanking() throws SQLException {
        List<Puntaje> lista = new ArrayList<>();
        String sql = "SELECT jugador, intentos, tiempo_segundos FROM ranking "
                + "ORDER BY intentos ASC, tiempo_segundos ASC, id ASC LIMIT 5";
        try (Connection con = ConexionBD.obtenerConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Puntaje(
                        rs.getString("jugador"),
                        rs.getInt("intentos"),
                        rs.getInt("tiempo_segundos")));
            }
        }
        return lista;
    }
}
