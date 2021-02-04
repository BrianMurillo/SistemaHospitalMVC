package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brian54
 */
public class UserDao {

    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarUser(User user) {
        String sql = "INSERT INTO registration(name,user_name,password,email_id,contact_no)VALUES(?,?,?,?,?)";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUser_name());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail_id());
            ps.setInt(5, user.getContact_no());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarUser() {
        List<User> ListaUsuario = new ArrayList();
        String sql = "SELECT * FROM registration";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //el rs se coloca antes de la primera fila y empieza a recorrer todas las filas de la tabla 
            while (rs.next()) {
                //Intanciamos el objeto cl para almacenar los datos obtenidos en la consulta
                User us = new User();
                us.setName(rs.getString("name"));
                us.setUser_name(rs.getString("user_name"));
                us.setPassword(rs.getString("password"));
                us.setEmail_id(rs.getString("email_id"));
                us.setContact_no(rs.getInt("contact_no"));
                //Iremos agregando al arraylist el objeto
                ListaUsuario.add(us);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        //Retornamos el arraylist del cliente
        return ListaUsuario;
    }

    public boolean ModificarUser(User user) {
        String sql = "UPDATE registration set name=?,password=?,email_id=?,contact_no=? WHERE user_name=?";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail_id());
            ps.setInt(4, user.getContact_no());
            ps.setString(5, user.getUser_name());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean EliminarUser(String user_name) {
        String sql = "DELETE FROM registration WHERE user_name=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user_name);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
