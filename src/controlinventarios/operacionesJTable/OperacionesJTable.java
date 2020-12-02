/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.operacionesJTable;

import controlinventarios.objects.Compras;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class OperacionesJTable {
    
    public void llenarTablaCompras(JTable miTabla, ArrayList<Compras> listaCompra){
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.setColumnIdentifiers(new Object[]{"PROVEEDOR","PRODUCTO",
        "PRECIO","KILOS", "FECHA"});
        for(int i = 0; i<listaCompra.size(); i++){
            Object[] datos = new Object[5];
            datos[0] = listaCompra.get(i).getProveedor();
            datos[1] = listaCompra.get(i).getProducto();
            datos[2] = listaCompra.get(i).getPrecio();
            datos[3] = listaCompra.get(i).getKilos();
            datos[4] = listaCompra.get(i).getFecha();
            modelo.addRow(datos);
        }
        
        miTabla.setModel(modelo);
    }
    
    public void agregarCompra(ArrayList listaCompra, String fecha, 
            String proveedor, String producto, int precio, double kilos){
        Compras compra = new Compras(fecha, proveedor, producto, precio, kilos);
        listaCompra.add(compra);
    }
    
    public void eliminarRegistroTabla(int index, ArrayList<Compras> listaCompra){
        listaCompra.remove(index);
    }
    
}