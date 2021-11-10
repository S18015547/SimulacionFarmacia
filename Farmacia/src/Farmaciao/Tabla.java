package Farmaciao;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Tabla {

    private DefaultTableModel modeloTabla;
    private javax.swing.JTable tabla;
    private ResultSet resultado = null;

   
    public Tabla(javax.swing.JTable tabla, String[][] datos, String[] cabecera) {
        this.tabla = tabla;
        modeloTabla = new DefaultTableModel(datos, cabecera);
        tabla.setModel(modeloTabla);
        this.modeloTabla = modeloTabla;
    }

    
    public void anadirLinea(String[] datos) {
        modeloTabla.addRow(datos);
    }
    
    
    public void llenarTabla() {
        resultado = Mysql.consultarInventario();
        
        try {
            ResultSetMetaData rsmd = resultado.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();

            while (resultado.next()) {
                String[] filas = new String[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = resultado.getString(i + 1);
                }
                anadirLinea(filas);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public void llenarTabla(String busqueda) {
        resultado = Mysql.consultarHorario(busqueda);

        try {
            ResultSetMetaData rsmd = resultado.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();

            while (resultado.next()) {
                String[] filas = new String[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = resultado.getString(i + 1);
                }
                anadirLinea(filas);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
     * Permite eliminar una fila de una tabla
     */
    public void eliminarFila() {
        
        /* Se obtiene la fila seleccionada en la tabla */
        int filaSeleccionada = tabla.getSelectedRow();
        
        /* Se obtiene la cantidad de filas seleccionadas en la tabla */
        int cantidadFilasSeleccionadas = tabla.getSelectedRowCount();
        int actualizacion = 0;
        String nrc;
        String dia;

        if (cantidadFilasSeleccionadas == 0) {
            
            /* Si la cantidad de filas seleccionadas es a igual a cero se muestra un ventana de informacion */
            JOptionPane.showMessageDialog(null, "Debes seleccionar al menos una fila", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        } else if (cantidadFilasSeleccionadas > 1) {
            
            /* Si la cantidad de filas seleccionadas es mayor a uno muestra un ventana de informacion */
            JOptionPane.showMessageDialog(null, "Solo debes seleccionar una fila", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            /*
             * Si ninguna de las anteriores condicionales es falsa se obtiene los datos de la columna 0 y 6,
             * posteriormente se llama al metodo eliminarRegistro
             */
            
            nrc = tabla.getValueAt(filaSeleccionada, 0).toString();
            dia = tabla.getValueAt(filaSeleccionada, 6).toString();
            actualizacion = Mysql.eliminarRegistro(nrc, dia);
        }

    }

}
