/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.consultas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author juanj
 */
public class ConsultasProveedores {
    
   public void llenarCMBProveedores(JComboBox cmb) throws SQLException{
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT nombre FROM usuarios WHERE tipo = 'proveedor'";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        
        while(res.next()){
            cmb.addItem(res.getString("nombre"));
        }
        miConexion.close();
    }
   
   public String obtenerUsuario(String nombre) throws SQLException{
       Connection miConexion = new ConexionBD().realizarConexion();
       String consulta = "SELECT id FROM usuarios WHERE nombre = '" +nombre+ "'";
       Statement sentencia = miConexion.createStatement();
       ResultSet res = sentencia.executeQuery(consulta);
       String usuario = "";
       if(res.next()){
           usuario = res.getString("id");
       }
       miConexion.close();
       miConexion.close();
       return usuario;
   }
}
