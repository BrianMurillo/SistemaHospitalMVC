
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
public class PacienteDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    Paciente paciente=new Paciente();
    
    public boolean RegistrarPaciente(Paciente paciente){
        String sql="INSERT INTO patientregistration(PatientID,PatientName,FatherName,Email,ContactNo,Age,Remarks,Gen,BG,Address)VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, paciente.getPatientID());
            ps.setString(2, paciente.getPatientName());
            ps.setString(3, paciente.getFatherName());
            ps.setString(4, paciente.getPatientEmail());
            ps.setString(5, paciente.getPatientContact());
            ps.setInt(6, paciente.getPatientAge());
            ps.setString(7, paciente.getPatientRemarks());
            ps.setString(8, paciente.getPatientGen());
            ps.setString(9, paciente.getPatientGrupoS());
            ps.setString(10, paciente.getPatientAddress());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ActualizarPaciente(Paciente paciente){
        String sql="UPDATE patientregistration SET PatientName=?,FatherName=?,Email=?,ContactNo=?,Age=?,Remarks=?,Gen=?,BG=?,Address=? WHERE PatientID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, paciente.getPatientName());
            ps.setString(2, paciente.getFatherName());
            ps.setString(3, paciente.getPatientEmail());
            ps.setString(4, paciente.getPatientContact());
            ps.setInt(5, paciente.getPatientAge());
            ps.setString(6, paciente.getPatientRemarks());
            ps.setString(7, paciente.getPatientGen());
            ps.setString(8, paciente.getPatientGrupoS());
            ps.setString(9, paciente.getPatientAddress()); 
            ps.setInt(10, paciente.getPatientID());
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
    
    public boolean BorrarPaciente(int paciente){
        String sql="DELETE FROM patientregistration WHERE PatientID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, paciente);
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
    
    public List ListarUser() {
        List<Paciente> ListaPaciente = new ArrayList();
        String sql = "SELECT * FROM patientregistration";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //el rs se coloca antes de la primera fila y empieza a recorrer todas las filas de la tabla 
            while (rs.next()) {
                Paciente patient=new Paciente();
                //Intanciamos el objeto cl para almacenar los datos obtenidos en la consulta
                patient.setPatientID(rs.getInt("PatientID"));
                patient.setPatientName(rs.getString("PatientName"));
                patient.setFatherName(rs.getString("FatherName"));
                patient.setPatientEmail(rs.getString("Email"));
                patient.setPatientContact(rs.getString("ContactNo"));
                patient.setPatientAge(rs.getInt("Age"));
                patient.setPatientRemarks(rs.getString("Remarks"));
                patient.setPatientGen(rs.getString("Gen"));
                patient.setPatientGrupoS(rs.getString("BG"));
                patient.setPatientAddress(rs.getString("Address"));
                //Iremos agregando al arraylist el objeto
                ListaPaciente.add(patient);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        //Retornamos el arraylist del cliente
        return ListaPaciente;
    }
    
    
}
