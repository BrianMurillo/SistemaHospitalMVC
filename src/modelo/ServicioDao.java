
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
public class ServicioDao {
    private Conexion conexion=new Conexion();
    private Servicio servicio=new Servicio();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public boolean RegistrarServicio(Servicio servicio){
        String sql="INSERT INTO services (ServiceName,ServiceDate,PatientID,ServicesCharges,ServiceID) VALUES(?,?,?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, servicio.getServiceName());
            ps.setString(2, servicio.getServiceDate());
            ps.setInt(3, servicio.getPatientId());
            ps.setInt(4,servicio.getServiceCharges());
            ps.setInt(5, servicio.getServiceId());
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
    
    public boolean ActualizarServicio(Servicio servicio){
        String sql="UPDATE services SET ServiceName=?,ServiceDate=?,PatientID=?,ServiceCharges=? WHERE ServiceID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, servicio.getServiceName());
            ps.setString(2, servicio.getServiceDate());
            ps.setInt(3, servicio.getPatientId());
            ps.setInt(4, servicio.getServiceCharges());
            ps.setInt(5, servicio.getServiceId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean BorrarServicio(int serviceId){
        String sql="DELETE services WHERE ServiceID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, serviceId);
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
    
    public List ListarServicio(){
        List<Servicio> ListaServicio= new ArrayList();
        String sql="SELECT * FROM services";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Servicio service=new Servicio();
                service.setServiceName(rs.getString("ServiceName"));
                service.setServiceDate(rs.getString("ServiceDate"));
                service.setPatientId(rs.getInt("PatientID"));
                service.setServiceCharges(rs.getInt("ServicesCharges"));
                service.setServiceId(rs.getInt("ServiceID"));    
                ListaServicio.add(service);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaServicio;
    }
    
}
