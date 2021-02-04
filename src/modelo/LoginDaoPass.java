/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brian54
 */
public class LoginDaoPass {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    
    
    public login ComfirmPassword(String user){
        login log=new login();
        String sql="SELECT * FROM users WHERE user_name=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, user);
            rs=ps.executeQuery();
            if(rs.next()){
                log.setUser_Name(rs.getString("user_name"));
                log.setPassword(rs.getString("password"));
            }                
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
        return log;
    }
    
    public boolean ChangePassword(login l){
        String sql="UPDATE users SET password=? WHERE user_name=?";
        try{
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, l.getPassword());
            ps.setString(2, l.getUser_Name());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        } finally  {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
