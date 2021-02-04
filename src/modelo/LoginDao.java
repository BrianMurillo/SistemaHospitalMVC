
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brian54
 */
public class LoginDao {
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private Conexion conexion=new Conexion();
    private login log=new login();
    
    public login getUsuario(String user,String pass){
        try{
            String sql="SELECT * FROM users WHERE user_name=? AND password=?";
            con=conexion.getConnection();
            ps=con.prepareCall(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            if(rs.next()){
                log.setUser_Name(rs.getString("user_name"));
                log.setPassword(rs.getString("password"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return log;
    }
    
    public List ListarAcceso() {
        List<login> ListaAcceso = new ArrayList();
        String sql = "SELECT * FROM users";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //el rs se coloca antes de la primera fila y empieza a recorrer todas las filas de la tabla 
            while (rs.next()) {
                //Intanciamos el objeto cl para almacenar los datos obtenidos en la consulta
                login logi=new login();
                logi.setUser_Name(rs.getString("user_name"));
                logi.setPassword(rs.getString("password"));
                //Iremos agregando al arraylist el objeto
                ListaAcceso.add(logi);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        //Retornamos el arraylist del cliente
        return ListaAcceso;
    }
}
