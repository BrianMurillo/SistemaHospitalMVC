
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
public class DoctorDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion= new Conexion();
    
    public boolean RegistrarDoctor(Doctor doctor){
        String sql="INSERT INTO doctor(DoctorID,DoctorName,FatherName,Email,ContacNo,Qualifications,Gender,BloodGroup,DateOfJoining,Address)VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, doctor.getDoctorID());
            ps.setString(2, doctor.getDoctorName());
            ps.setString(3, doctor.getDoctorFatherName());
            ps.setString(4, doctor.getDoctorEmail());
            ps.setString(5, doctor.getContactNo());
            ps.setString(6, doctor.getQualifications());
            ps.setString(7, doctor.getGender());
            ps.setString(8, doctor.getBloodGroup());
            ps.setString(9, doctor.getDateOfJoin());
            ps.setString(10, doctor.getAddress());
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
    
    public boolean ActualizarDoctor(Doctor doctor){
        String sql="UPDATE doctor SET DoctorName=?,FatherName=?,Email=?,ContacNo=?,Qualifications=?,Gender=?,BloodGroup=?,DateOfJoining=?,Address=? WHERE DoctorID=?";
    try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, doctor.getDoctorName());
            ps.setString(2, doctor.getDoctorFatherName());
            ps.setString(3, doctor.getDoctorEmail());
            ps.setString(4, doctor.getContactNo());
            ps.setString(5, doctor.getQualifications());
            ps.setString(6, doctor.getGender());
            ps.setString(7, doctor.getBloodGroup());
            ps.setString(8, doctor.getDateOfJoin());
            ps.setString(9, doctor.getAddress());           
            ps.setInt(10, doctor.getDoctorID());
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
    
    public boolean borrarDoctor(int DoctorID){
        String sql="DELETE FROM doctor WHERE DoctorID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, DoctorID);
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
    
    public List ListarDoctor(){
        List<Doctor> ListaDoctor = new ArrayList();
        String sql="SELECT * FROM doctor";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();           
            while(rs.next()){
                Doctor doctor = new Doctor();
                doctor.setDoctorID(rs.getInt("DoctorID"));
                doctor.setDoctorName(rs.getString("DoctorName"));
                doctor.setDoctorFatherName(rs.getString("FatherName"));
                doctor.setDoctorEmail(rs.getString("Email"));
                doctor.setContactNo(rs.getString("ContacNo"));
                doctor.setQualifications(rs.getString("Qualifications"));
                doctor.setGender(rs.getString("Gender"));
                doctor.setBloodGroup(rs.getString("BloodGroup"));
                doctor.setDateOfJoin(rs.getString("DateOfJoining"));
                doctor.setAddress(rs.getString("Address"));
                ListaDoctor.add(doctor);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaDoctor;
    }
}
