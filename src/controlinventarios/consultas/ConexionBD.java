/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.consultas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author juanj
 */
public class ConexionBD {
    
    String URL_CONEXION = "jdbc:postgresql://localhost:5432/control_inventarios";
    
    public Connection realizarConexion() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL_CONEXION, "postgres", "postgresql");
        } catch (Exception e) {
            System.out.println("Ocurrio un error : " + e.getMessage());
        }
        return conn;
    }
    
}
