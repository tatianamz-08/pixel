package conexion;

import java.sql.*;

public class Conexion {

    public Connection getConnectDB(){

        Connection connection = null;

        try{

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pixel", "root", "tatiana123");
            if(connection != null){
                System.out.println("Database is connected!!!");
            }


        }catch (SQLException e){

            System.out.println(e);
        }

        return connection;
    }

    public void obtenerDatos() {
        Connection connection = getConnectDB();
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
        controller.Conexion conexion = new controller.Conexion();
        conexion.obtenerDatos();
    }




}
