package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {

    public Connection getConnectionDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pixel", "root", "tatiana123");
            if (connection != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException e) {
            System.out.println("Ha fallado la conexión");
            e.printStackTrace();
        }
        return connection;
    }

    public void obtenerDatos() {
        Connection connection = getConnectionDB();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            if (connection != null) {
                System.out.println("Ejecutando consulta SQL...");
                String query = "SELECT * FROM Drones";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);

                System.out.println("Consulta ejecutada, procesando resultados...");

                if (!rs.isBeforeFirst()) {
                    System.out.println("No se encontraron datos en la tabla Drones.");
                } else {
                    while (rs.next()) {
                        int id_drone = rs.getInt("id");
                        String modelo = rs.getString("modelo");
                        String fabricante = rs.getString("fabricante");

                        System.out.println("ID: " + id_drone + ", Modelo: " + modelo + ", Fabricante: " + fabricante);
                    }
                }
            } else {
                System.out.println("Conexión no establecida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
            // Datos de la base de datos
            String url = "jdbc:mysql://localhost:3306/pixel"; // Reemplaza 'tu_base_de_datos' con el nombre de tu base de datos
            String user = "root"; // Reemplaza 'tu_usuario' con tu usuario de MySQL
            String password = "tatiana123"; // Reemplaza 'tu_contraseña' con tu contraseña de MySQL

            // Consulta SQL para insertar datos
            String sql = "INSERT INTO Drones (id, modelo, fabricante) VALUES (?, ?, ?)"; // Reemplaza 'tu_tabla' y las columnas correspondientes

            try {
                // Establecer la conexión
                Connection conn = DriverManager.getConnection(url, user, password);

                // Crear el PreparedStatement
                PreparedStatement pstmt = conn.prepareStatement(sql);

                // Establecer los valores de los parámetros
                pstmt.setString(1, "6"); // Reemplaza 'valor1' con el valor para columna1
                pstmt.setString(2, "mj987"); // Reemplaza 'valor2' con el valor para columna2
                pstmt.setString(3, "mpa"); // Reemplaza 'valor2' con el valor para columna2

                // Ejecutar la consulta
                int filasInsertadas = pstmt.executeUpdate();

                // Verificar si la inserción fue exitosa
                if (filasInsertadas > 0) {
                    System.out.println("¡Inserción exitosa!");
                }

                // Cerrar la conexión y el PreparedStatement
                pstmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
