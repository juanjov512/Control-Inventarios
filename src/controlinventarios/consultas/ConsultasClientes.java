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
import javax.swing.JOptionPane;

/**
 *
 * @author juanj
 */
public class ConsultasClientes {

    public void llenarCMBClientes(JComboBox cmb) throws SQLException {
        Connection conexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT nombre FROM usuarios WHERE tipo = 'cliente'";
        Statement sentencia = conexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);

        while (res.next()) {
            cmb.addItem(res.getString("nombre"));
        }
        conexion.close();
    }

    public String obtenerUsuario(String nombre) throws SQLException {
        Connection conexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT id FROM usuarios WHERE"
                + " nombre = '" + nombre + "' AND tipo = 'cliente'";
        Statement sentencia = conexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        String usuario = "";
        if (res.next()) {
            usuario = res.getString("id");
        }
        conexion.close();
        return usuario;
    }

    public boolean agregarCliente(String nombre) throws SQLException {
        Connection conexion = new ConexionBD().realizarConexion();
        if (!existe(nombre)) {
            String consulta = "INSERT INTO usuarios(nombre, tipo) values "
                    + "('" + nombre + "', 'cliente')";
            Statement sentencia = conexion.createStatement();
            sentencia.execute(consulta);
            JOptionPane.showMessageDialog(null, "Cliente agregado con éxito!");
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "El cliente ya existe!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean existe(String nombre) throws SQLException {
        return obtenerUsuario(nombre) != "";
    }
}

