
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
public class RoomDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    
    public boolean RegistrarRoom(Room room){
        String sql="INSERT INTO room(RoomNo,RoomType,RoomCharges,RoomStatus)VALUES(?,?,?,?)";
        try {
            String status="Vacant";
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, room.getRoomNo());
            ps.setString(2, room.getRoomType());
            ps.setInt(3, room.getRoomCharges());
            ps.setString(4, status);
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
    
    public boolean ActualizarRoom(Room room){
        String sql="UPDATE room SET RoomType=?,RoomCharges=? WHERE RoomNo=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, room.getRoomType());
            ps.setInt(2, room.getRoomCharges());
            ps.setInt(3, room.getRoomNo());
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
    
    public boolean BorrarRoom(int RoomNo){
        String sql="DELETE FROM room WHERE RoomNo=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, RoomNo);
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
    
    public List ListarRoom(){
        List<Room> ListaRoom = new ArrayList(); 
        String sql="SELECT * FROM room;";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Room room = new Room();
                room.setRoomNo(rs.getInt("RoomNo"));
                room.setRoomType(rs.getString("RoomType"));
                room.setRoomCharges(rs.getInt("RoomCharges"));
                room.setRoomStatus(rs.getString("RoomStatus"));
                ListaRoom.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaRoom;
    }   
}
