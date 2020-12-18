/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.consultas;

import controlinventarios.objects.Ventas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class ConsultasVentas {
    
    public void llenarTabla(JTable miTabla) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT V.id, U.nombre as usuario, P.nombre, precio, "
                + "kilos FROM ventas V INNER JOIN productos P "
                + "ON V.id_productos = P.id INNER JOIN usuarios U "
                + "ON V.id_usuario = U.id";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"CODIGO","CLIENTE","PRODUCTO",
        "PRECIO","KILOS"});
        while(res.next()){
            modelo.addRow(new Object[]{res.getString("id"),
                res.getString("usuario"), res.getString("nombre"), 
                res.getString("precio"), res.getString("kilos")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }
    
    public void agregarRegistros(ArrayList<Ventas> lista) throws SQLException{
        ConsultasProductos productos = new ConsultasProductos();
        Connection miConexion = new ConexionBD().realizarConexion();
        ConsultasProveedores proveedor = new ConsultasProveedores();
        Statement sentencia = miConexion.createStatement();
        
        for(int i=0; i < lista.size(); i++){
            String producto = productos.obtenerProducto(lista.get(i).getProducto());
            String fecha = lista.get(i).getFecha();
            String usuario = proveedor.obtenerUsuario(lista.get(i).getProveedor());
            int precio = lista.get(i).getPrecio();
            double kilos = lista.get(i).getKilos();
            String consulta = "INSERT INTO ventas (id_productos, precio, kilos,"
                + " fecha, id_usuario) values ('"+producto+"', '"+precio+"', "
                + "'"+kilos+"', '"+fecha+"', '"+usuario+"')";
            sentencia.execute(consulta);
        }
        
        miConexion.close();
    }
    
    
    
}