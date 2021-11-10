package Farmaciao;



import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Mysql {

    private static PreparedStatement consulta;
    private static ResultSet resultadoConsulta;
    private static Connection conexion;
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String USUARIO = "root";
    private static final String CONTRASENIA = "";
    private static final String DIRECCION = "jdbc:mysql://localhost:3306/farmacia";
    
     /*
     * Establece una conexion con la base de datos mediante el controlador
     */
    public static Connection getConnection() {    
        try {
            Class.forName(CONTROLADOR);
            conexion = (Connection) DriverManager.getConnection(DIRECCION, USUARIO, CONTRASENIA);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion", "ERROR", JOptionPane.ERROR_MESSAGE);
        }        
        return conexion;
    }

    /*
     * Obtiene el contenido de la tabla horario de la base de datos
     * recibe un parametro de busqueda para filtrar los resultados de la consulta
     */
    public static ResultSet consultarHorario(String busqueda) {
        try {
            conexion = getConnection();
            consulta = conexion.prepareStatement("SELECT * FROM HORARIO WHERE CONCAT(nrc,' ',materia,' ',docente,' ',salon) LIKE '%" + busqueda + "%'");
            resultadoConsulta = consulta.executeQuery();            
            return resultadoConsulta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el Horario", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultadoConsulta;
    }

    public static ResultSet consultarInventario() {
        try {
            conexion = getConnection();
            consulta = conexion.prepareStatement("SELECT * FROM medicamentos ORDER BY idmedicamentos;");
            resultadoConsulta = consulta.executeQuery();
            return resultadoConsulta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar a tu hermana", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultadoConsulta;
    }

    /*
     * Elimina un registro de la tabla horario, recibiendo como parametros nrc y dia
     * retorna el numero de filas afectadas
     */
    public static int eliminarRegistro(String nrc, String dia) {
        int actualizacion = 0;
        
       try {
            conexion = getConnection();
            consulta = conexion.prepareStatement("DELETE FROM HORARIO WHERE NRC = ? AND DIA = ?");
            consulta.setString(1, nrc);
            consulta.setString(2, dia);
            actualizacion = consulta.executeUpdate();
            
            if (actualizacion > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro, vuelve a intentarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return actualizacion;
    }
    /*
     * Almacena los datos recibidos como parametros en la Base de Datos. Estos parametros 
     * deben ser enviados de manera ordenada a como seran insertados en la tabla de la BD. 
     */
    public static void guardarMateria(String nrc, String materia, String docente, String horaInicio, String horaFinal, String salon, String dia) {
        try {
            conexion = getConnection();            
            consulta = conexion.prepareStatement("INSERT INTO `horario` (`nrc`, `materia`, `docente`, `horaInicio`, `horaFinal`, `salon`, `dia`) VALUES (?,?,?,?,?,?,?);");
            /*Asigna los parametros a la consulta declarada*/
            consulta.setString(1, nrc);
            consulta.setString(2, materia);
            consulta.setString(3, docente);
            consulta.setString(4, horaInicio);
            consulta.setString(5, horaFinal);
            consulta.setString(6, salon);
            consulta.setString(7, dia);
            consulta.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
