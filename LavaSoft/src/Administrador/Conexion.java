/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

/**
 *
 * @author soria
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:sqlserver://adnsoria.database.windows.net:1433;databaseName=LavaSoft;encrypt=true;trustServerCertificate=true";
    private static final String USER = "admin1@adnsoria";
    private static final String PASSWORD = "Ad@ncode123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
