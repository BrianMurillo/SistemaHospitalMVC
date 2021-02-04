
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brian54
 */
public class Conexion {
    private String Url="jdbc:mysql://localhost:3307/hp_mgmt_db";
    private String user="root";
    private String pass="";
    Connection con=null;
    
    public Connection getConnection(){
        try {
            con=DriverManager.getConnection(Url, user, pass);
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString()); 
        } 
        return null;
    }
}
