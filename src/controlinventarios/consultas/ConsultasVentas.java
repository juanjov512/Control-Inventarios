/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.consultas;

import controlinventarios.objects.Ventas;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class ConsultasVentas {
    
    DecimalFormat formateo = new DecimalFormat("###,###.##");

    public void llenarTabla(JTable miTabla) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT V.id as id, U.nombre as usuario, P.nombre, "
                + "precio, kilos, fecha FROM ventas V INNER JOIN productos P "
                + "ON V.id_productos = P.id INNER JOIN usuarios U "
                + "ON V.id_usuario = U.id ORDER BY id DESC";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID", "CLIENTE", "PRODUCTO",
            "PRECIO", "KILOS", "FECHA"});
        while (res.next()) {
            modelo.addRow(new Object[]{res.getString("id"), res.getString("usuario"),
                res.getString("nombre"), formateo.format(res.getLong("precio")),
                formateo.format(res.getDouble("kilos")), res.getString("fecha")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }

    public void busquedaTabla(JTable miTabla, String proveedor) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT V.id as id, U.nombre as usuario, P.nombre, "
                + "precio, kilos, fecha FROM ventas V INNER JOIN productos P "
                + "ON V.id_productos = P.id INNER JOIN usuarios U "
                + "ON V.id_usuario = U.id WHERE U.nombre LIKE '%" + proveedor + "%' "
                + "ORDER BY id DESC";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID", "CLIENTE", "PRODUCTO",
            "PRECIO", "KILOS", "FECHA"});
        while (res.next()) {
            modelo.addRow(new Object[]{res.getString("id"), res.getString("usuario"),
                res.getString("nombre"), res.getString("precio"),
                res.getString("kilos"), res.getString("fecha")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }

    public void agregarRegistros(ArrayList<Ventas> lista) throws SQLException {
        ConsultasProductos productos = new ConsultasProductos();
        Connection miConexion = new ConexionBD().realizarConexion();
        ConsultasClientes cliente = new ConsultasClientes();
        Statement sentencia = miConexion.createStatement();

        for (int i = 0; i < lista.size(); i++) {
            String producto = productos.obtenerProducto(lista.get(i).getProducto());
            String fecha = lista.get(i).getFecha();
            String usuario = cliente.obtenerUsuario(lista.get(i).getProveedor());
            int precio = lista.get(i).getPrecio();
            double kilos = lista.get(i).getKilos();
            BigInteger total = BigDecimal.valueOf(precio*kilos).toBigInteger();
            String consulta = "INSERT INTO ventas (id_productos, precio, kilos,"
                    + " fecha, id_usuario, total) values ('" + producto + "', '" + precio + "', "
                    + "'" + kilos + "', '" + fecha + "', '" + usuario + "', '" + total + "')";
            sentencia.execute(consulta);
        }

        miConexion.close();
    }

    public void editarRegistro(Ventas v, int id) throws SQLException {
        ConsultasProductos productos = new ConsultasProductos();
        Connection miConexion = new ConexionBD().realizarConexion();
        ConsultasClientes cliente = new ConsultasClientes();
        Statement sentencia = miConexion.createStatement();
        String producto = productos.obtenerProducto(v.getProducto());
        String fecha = v.getFecha();
        String usuario = cliente.obtenerUsuario(v.getProveedor());
        int precio = v.getPrecio();
        double kilos = v.getKilos();
        BigInteger total = BigDecimal.valueOf(precio * kilos).toBigInteger();
        String consulta = "UPDATE ventas SET id_productos = " + producto + ", "
                + "precio = " + precio + ", kilos = " + kilos + ", fecha = '" + fecha + "', "
                + "id_usuario =" + usuario + ", total = " + total + " WHERE id = " + id + "";
        sentencia.execute(consulta);
        miConexion.close();
    }

    public void eliminarRegistro(int id) throws SQLException {
        Connection miConexion = new ConexionBD().realizarConexion();
        Statement sentencia = miConexion.createStatement();
        String consulta = "DELETE FROM ventas where id = " + id + "";
        sentencia.execute(consulta);
        miConexion.close();
    }

}
