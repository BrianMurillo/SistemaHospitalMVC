
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
public class PacienteAdmitidoDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    
    public boolean RegitrarPacienteA(PacienteAdmitido pacienteAdmitido){
        String sql="INSERT INTO admitpatient_room(PatientID,Disease,AdmitDate,RoomNo,DoctorID,AP_Remarks)VALUES(?,?,?,?,?,?)";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, pacienteAdmitido.getPatientID());
            ps.setString(2, pacienteAdmitido.getDisease());
            ps.setString(3, pacienteAdmitido.getAdmintDate());
            ps.setInt(4, pacienteAdmitido.getRoomNo());
            ps.setInt(5, pacienteAdmitido.getDoctorID());
            ps.setString(6, pacienteAdmitido.getAp_Remarks());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    } 
    
    public boolean VerificarRoom(int RoomNo){
        String sql="SELECT RoomNo FROM room WHERE RoomNo=? AND RoomStatus=?";
        String status="Vacant";
        try{
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, RoomNo);
            ps.setString(2, status);
            rs=ps.executeQuery();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }      
    }
    
    public boolean ActulizarRoom(PacienteAdmitido pacienteAdmitido){
        String sql="UPDATE room SET RoomStatus=? WHERE RoomNo=?";
        String status="Booked";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, pacienteAdmitido.getRoomNo());           
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
    
    public boolean ActualizarRoomV(PacienteAdmitido pacienteAdmitido){
        String sql="UPDATE room SET RoomStatus=? WHERE RoomNo=?";
        String status="Vacant";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, pacienteAdmitido.getRoomNo());
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
    
    public boolean ActualizarPacienteA(PacienteAdmitido pacienteAdmitido){
        String sql="UPDATE admitpatient_room SET Disease=?,AdmitDate=?,RoomNo=?,DoctorID=?,AP_Remarks=? WHERE PatientID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, pacienteAdmitido.getDisease());
            ps.setString(2, pacienteAdmitido.getAdmintDate());
            ps.setInt(3, pacienteAdmitido.getRoomNo());
            ps.setInt(4, pacienteAdmitido.getDoctorID());
            ps.setString(5, pacienteAdmitido.getAp_Remarks());      
            ps.setInt(6, pacienteAdmitido.getPatientID());
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
    
    public boolean BorrarPacienteA(int patientID){
        String sql="DELETE FROM admitpatient_room WHERE PatientID=?";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, patientID);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    } 
    
    public List ListarPacienteA(){
       List<PacienteAdmitido> ListaPacienteA = new ArrayList();
       String sql="Select PatientRegistration.PatientID as 'Patient ID',PatientRegistration.PatientName,PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Room.RoomNo as 'Room No',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',AdmitPatient_Room.AP_Remarks as 'Remarks' from Room,Doctor,PatientRegistration,AdmitPatient_Room where Room.RoomNo=AdmitPatient_Room.RoomNo and Doctor.DoctorID=AdmitPatient_Room.DoctorID and PatientRegistration.PatientID=AdmitPatient_Room.PatientID order by admitdate";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                PacienteAdmitido pa = new PacienteAdmitido();
                pa.setPatientID(rs.getInt("Patient ID"));
                pa.setPatientName(rs.getString("PatientName"));
                pa.setGen(rs.getString("Gender"));
                pa.setDisease(rs.getString("Disease"));
                pa.setAdmintDate(rs.getString("Admit Date"));
                pa.setBlood(rs.getString("Blood Group"));
                pa.setRoomNo(rs.getInt("Room No"));
                pa.setDoctorID(rs.getInt("Doctor ID"));
                pa.setDoctorName(rs.getString("Doctor Name"));
                pa.setAp_Remarks(rs.getString("Remarks"));
                ListaPacienteA.add(pa);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPacienteA;
    }
    
    public List ListarDoctor(){
        List<Doctor> ListaDoctor = new ArrayList();
        String sql="SELECT * FROM doctor";
        try{
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Doctor doctor= new Doctor();
                doctor.setDoctorID(rs.getInt("DoctorID"));
                doctor.setDoctorName(rs.getString("DoctorName"));
                ListaDoctor.add(doctor);
            }
        } catch(SQLException e){
            System.out.println(e.toString());
        }
       return ListaDoctor;     
    }
    
    public List ListarPaciente(){
        List<Paciente> ListaPaciente = new ArrayList();
        String sql="SELECT * FROM patientregistration";
        try {
            con=conexion.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                Paciente paciente=new Paciente();
                paciente.setPatientID(rs.getInt("PatientID"));
                paciente.setPatientName(rs.getString("PatientName"));
                paciente.setPatientGen(rs.getString("Gen"));
                paciente.setPatientGrupoS(rs.getString("BG"));
                ListaPaciente.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPaciente;
    }
}
    

